package com.ec.sticket.services;

import com.ec.sticket.models.Asset;
import com.ec.sticket.repositories.AssetRepository;
import com.ec.sticket.util.ApiMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Asset findById(int assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        return asset.orElseGet(Asset::new);
    }

    public ApiMessage save(Asset asset) {
        if (asset != null && asset.getAuthor() != null) {
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

    public List<Asset> findAssetsByThemeId(int assetId) {
        return assetRepository.findAllByThemeId(assetId);
    }
}
