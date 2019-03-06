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

    public List<Asset> findAssetsByAuthorId(int authorId) {
        return assetRepository.findAllByAuthorId(authorId);
    }

    public List<Asset> findAssetsByBuyerId(int buyerId) {
        return assetRepository.findAllByBuyerId(buyerId);
    }

    public List<Asset> findAssetsByStickerId(int stickerId) {
        return assetRepository.findAllByStickerId(stickerId);
    }

    public List<Asset> findAssetsByLandmark(Asset.Landmark landmark) {
        return assetRepository.findAllByLandmark(landmark);
    }

    public List<Asset> findAssetsByThemeId(int themeId) {
        return assetRepository.findAllByThemeId(themeId);
    }
}
