package com.ec.sticket.services;

import com.ec.sticket.models.Asset;
import com.ec.sticket.repositories.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    // Methods
    public void addAsset(Asset asset) {
        assetRepository.save(asset);
    }

    public Asset getAssetById(int assetId) {
        Optional<Asset> assetOptional = assetRepository.findById(assetId);
        return assetOptional.orElseGet(Asset::new);
    }

    public List<Asset> getAssetsByAuthorId(int authorId) {
        return assetRepository.findAllByAuthorId(authorId);
    }

    public List<Asset> getAssetsByBuyerId(int buyerId) {
        return assetRepository.findAllByBuyerId(buyerId);
    }

    public List<Asset> getAssetsByStickerId(int stickerId) {
        return assetRepository.findAllByStickerId(stickerId);
    }

    public List<Asset> getAssetsByLandmarkId(int landmarkId) {
        return assetRepository.findAllByLandmarkId(landmarkId);
    }

    public List<Asset> getAssetsByThemeId(int themeId) {
        return assetRepository.findAllByThemeId(themeId);
    }
}
