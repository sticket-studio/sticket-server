package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Asset;
import com.ec.sticket.services.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/normal/asset")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{assetId}")
    public Asset getAssetById(@PathVariable("assetId") int assetid) {
        return assetService.getAssetById(assetid);
    }

    @GetMapping("author/{authorId}")
    public List<Asset> getAssetsByAuthorId(@PathVariable("authorId") int authorId) {
        return assetService.findAssetsByAuthorId(authorId);
    }

    @GetMapping("buyer/{buyerId}")
    public List<Asset> getAssetsByBuyerId(@PathVariable("buyerId") int buyerId) {
        return assetService.findAssetsByBuyerId(buyerId);
    }

    @GetMapping("sticker/{stickerId}")
    public List<Asset> getAssetsByStickerId(@PathVariable("stickerId") int stickerId) {
        return assetService.findAssetsByStickerId(stickerId);
    }

    @GetMapping("landmark/{landmark}")
    public List<Asset> getAssetsByLandmarkId(@PathVariable("landmark") Asset.Landmark landmark) {
        return assetService.findAssetsByLandmark(landmark);
    }

    @GetMapping("theme/{themeId}")
    public List<Asset> getAssetsByThemeId(@PathVariable("themeId") int themeId) {
        return assetService.findAssetsByThemeId(themeId);
    }
}
