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

    public Asset getAssetByid(int themeid) {
        Optional<Asset> assetOptional = assetRepository.findById(themeid);
        return assetOptional.orElseGet(Asset::new);
    }

    public List<Asset> getAssetsByAuthorid(int authorId) {
        return assetRepository.findAllByAuthorId(authorId);
    }

    public List<Asset> getAssetsByBuyerId(int buyerId) {
        return assetRepository.getAllByBuyerId(buyerId);
    }

    public List<Asset> getAssetsByStickerId(int stickerId) {
        return assetRepository.getAllByStickerId(stickerId);
    }

    public List<Asset> getAssetsByLandmarkId(int landmarkId) {
        return assetRepository.findAllByLandmarkId(landmarkId);
    }

    public List<Asset> getAssetsByThemeId(int themeId) {
        return assetRepository.getAllByThemeId(themeId);
    }
}
