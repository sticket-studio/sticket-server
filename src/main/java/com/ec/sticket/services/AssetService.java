package com.ec.sticket.services;

import com.ec.sticket.dto.request.user.AssetLikeRequest;
import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    // for caching
    private final List<Asset> todayAssets;
    private final List<Asset> popularAssets;
    private LocalDate lastUpdateTodayAssets;
    private LocalDate lastUpdatePopularAssets;

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public AssetService(UserRepository userRepository, AssetRepository assetRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
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

    public ApiMessage update(Asset modified) {
        Optional<Asset> assetOptional = assetRepository.findById(modified.getId());
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
            return ApiMessage.getFailMessage();
        }
    }

    public ApiMessage delete(int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            assetRepository.deleteById(assetId);
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    @Transactional
    public ApiMessage like(AssetLikeRequest request) {
        Optional<Asset> asset = assetRepository.findById(request.getAssetId());
        if (asset.isPresent()) {
            assetRepository.like(request.getUserId(), request.getAssetId());
            return ApiMessage.getSuccessMessage();
        } else {
            return ApiMessage.getFailMessage();
        }
    }

    public List<Asset> findFreeAssets() {
        return assetRepository.findAllByPriceBetween(0, 0);
    }

    public List<Asset> findAssetsByQuery(int authorId, int buyerId, int sticonId, String landmark, int themeId) {
        return assetRepository.findAllByQuery(authorId, buyerId, sticonId, landmark, themeId);
    }

    public List<Asset> findTodayAssets() {
        // Caching today's assets
        if (this.todayAssets.isEmpty() || lastUpdateTodayAssets.isBefore(LocalDate.now())) {
            this.todayAssets.clear();
            this.todayAssets.addAll(assetRepository.findTodayAssets());
        }
        return this.todayAssets;
    }

    public List<Asset> findPopularAssets() {
        // Caching popular assets
        if (this.popularAssets.isEmpty() || lastUpdatePopularAssets.isBefore(LocalDate.now())) {
            this.todayAssets.addAll(assetRepository.findPopularAssets());
        }
        return this.popularAssets;
    }
}
