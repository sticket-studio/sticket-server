package com.ec.sticket.services;

import com.ec.sticket.dto.request.user.AssetLikeRequest;
import com.ec.sticket.models.Asset;
import com.ec.sticket.models.User;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.repositories.UserRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public AssetService(UserRepository userRepository, AssetRepository assetRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
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
            asset.setThemes(modified.getThemes());
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

    public List<Asset> findAssetsByAuthorId(int authorId) {
        return assetRepository.findAllByAuthorId(authorId).orElseGet(ArrayList::new);
    }

    public List<Asset> findAssetsByBuyerId(int buyerId) {
        return assetRepository.findAllByBuyerId(buyerId);
    }

    public List<Asset> findAssetsBySticonId(int sticonId) {
        return assetRepository.findAllBySticonId(sticonId);
    }

    public List<Asset> findAssetsByLandmark(Asset.Landmark landmark) {
        return assetRepository.findAllByLandmark(landmark);
    }

    public List<Asset> findAssetsByThemeId(int assetId) {
        return assetRepository.findAllByThemeId(assetId);
    }
}
