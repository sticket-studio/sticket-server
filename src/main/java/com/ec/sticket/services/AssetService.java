package com.ec.sticket.services;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.models.mapping.UserLikeAsset;
import com.ec.sticket.models.mapping.UserPurchaseAsset;
import com.ec.sticket.models.mapping.compositekey.UserLikeAssetKey;
import com.ec.sticket.models.mapping.compositekey.UserPurchaseAssetKey;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.repositories.mapping.like.UserLikeAssetRepository;
import com.ec.sticket.repositories.mapping.purchase.UserPurchaseAssetRepository;
import com.ec.sticket.util.ApiMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssetService {
    private static final int PAGE_SIZE = 20;

    // for caching
    private final List<Asset> todayAssets;
    private final List<Asset> popularAssets;
    private LocalDate lastUpdateTodayAssets;
    private LocalDate lastUpdatePopularAssets;

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final UserLikeAssetRepository userLikeAssetRepository;
    private final UserPurchaseAssetRepository userPurchaseAssetRepository;

    public AssetService(UserRepository userRepository, AssetRepository assetRepository,
                        UserLikeAssetRepository userLikeAssetRepository,
                        UserPurchaseAssetRepository userPurchaseAssetRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.userLikeAssetRepository = userLikeAssetRepository;
        this.userPurchaseAssetRepository = userPurchaseAssetRepository;
        this.todayAssets = new ArrayList<>();
        this.popularAssets = new ArrayList<>();
        // 처음에 업데이트 하기 위해 하루 전으로 세팅
        this.lastUpdateTodayAssets = LocalDate.now().minusDays(1);
        this.lastUpdatePopularAssets = LocalDate.now().minusDays(1);
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Asset findById(int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        return asset.orElseGet(Asset::new);
    }

    public ApiMessage save(int authorId, Asset asset) {
        Optional<User> authorOptional = userRepository.findById(authorId);

        if (asset != null && authorOptional.isPresent()) {
            asset.setAuthor(authorOptional.get());

            assetRepository.save(asset);

            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage update(int assetId, Asset modified) {
        Optional<Asset> assetOptional = assetRepository.findById(assetId);
        if (assetOptional.isPresent()) {
            Asset asset = assetOptional.get();

            asset.setName(modified.getName());
            asset.setDescription(modified.getDescription());
            asset.setImgUrl(modified.getImgUrl());
            asset.setTheme(modified.getTheme());
            asset.setPrice(modified.getPrice());
            asset.setLandmark(modified.getLandmark());

            assetRepository.save(asset);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist");
        }
    }

    public ApiMessage delete(int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            assetRepository.deleteById(assetId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist");
        }
    }

    public List<Asset> findFreeAssets() {
        return assetRepository.findAllByPriceBetween(0, 0);
    }

    public List<Asset> findAssetsByQuery(int authorId, int buyerId, String landmark, int themeId) {
        return assetRepository.findAllByQuery(authorId, buyerId, landmark, themeId);
    }

    public List<Asset> findTodayAssets(int page) {
        // Caching today's assets
        if (this.todayAssets.isEmpty() || lastUpdateTodayAssets.isBefore(LocalDate.now())) {
            this.todayAssets.clear();
            this.todayAssets.addAll(assetRepository.findTodayAssets(PageRequest.of(page, PAGE_SIZE)));
        }
        return this.todayAssets;
    }

    public List<Asset> findPopularAssets(int page) {
        // Caching popular assets
        if (this.popularAssets.isEmpty() || lastUpdatePopularAssets.isBefore(LocalDate.now())) {
            this.todayAssets.addAll(assetRepository.findPopularAssets(PageRequest.of(page, PAGE_SIZE)));
        }
        return this.popularAssets;
    }

    @Transactional
    public ApiMessage like(User user, int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        UserLikeAssetKey userLikeAssetKey = new UserLikeAssetKey(user.getId(), assetId);

        if (!asset.isPresent()) {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist");
        } else if (userLikeAssetRepository.findById(userLikeAssetKey).isPresent()) {
            return ApiMessage.getFailMessage(String.format("User with id [%d] already like Asset with id [%d]",
                    user.getId(), assetId));
        } else {
            userLikeAssetRepository.save(new UserLikeAsset(userLikeAssetKey));
            assetRepository.save(asset.get());
            asset.get().setLikeCnt(asset.get().getLikeCnt() + 1);
            return ApiMessage.getSuccessMessage();
        }
    }

    @Transactional
    public ApiMessage dislike(User user, int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        UserLikeAssetKey userLikeAssetKey = new UserLikeAssetKey(user.getId(), assetId);

        if (!asset.isPresent()) {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist");
        } else if(!userLikeAssetRepository.findById(userLikeAssetKey).isPresent()){
            return ApiMessage.getFailMessage(String.format("User with id [%d] don't like Asset with id [%d]",
                    user.getId(), assetId));
        } else{
            userLikeAssetRepository.deleteById(userLikeAssetKey);
            assetRepository.save(asset.get());
            asset.get().setLikeCnt(asset.get().getLikeCnt() - 1);
            return ApiMessage.getSuccessMessage();
        }
    }

    public ApiMessage checkLike(User user, int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            return ApiMessage.getSuccessMessage(userLikeAssetRepository.findById(new UserLikeAssetKey(user.getId(), assetId)).isPresent());
        } else {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist");
        }
    }

    public List<Asset> getUsersLikeAssets(User user) {
        return userLikeAssetRepository.findAllByUser(user).stream().map(UserLikeAsset::getAsset).collect(Collectors.toList());
    }

    @Transactional
    public ApiMessage purchaseAsset(User user, int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            if (user.getStick() > asset.get().getPrice()) {
                userPurchaseAssetRepository.save(new UserPurchaseAsset(new UserPurchaseAssetKey(user.getId(), assetId)));
                user.setStick(user.getStick() - asset.get().getPrice());
                return ApiMessage.getSuccessMessage();
            } else {
                // Stick이 충분하지 않으면 Fail
                return ApiMessage.getFailMessage("Not enough stick. Please charge your stick.");
            }
        } else {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist.");
        }
    }

    public ApiMessage checkPurchase(User user, int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            return ApiMessage.getSuccessMessage(userPurchaseAssetRepository.findById(new UserPurchaseAssetKey(user.getId(), assetId)).isPresent());
        } else {
            return ApiMessage.getFailMessage("The asset with id [" + assetId + "] doesn't exist");
        }
    }

    public List<Asset> getUsersBoughtAssets(User user) {
        return userPurchaseAssetRepository.findAllByUser(user).stream().map(UserPurchaseAsset::getAsset).collect(Collectors.toList());
    }
}
