package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Asset;
import com.ec.sticket.services.AssetService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/asset")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("")
    public List<Asset> findAllAssets(){
        return assetService.findAll();
    }

    @GetMapping("/{assetId}")
    public Asset findAssetById(@PathVariable("assetId") int assetId){
        return assetService.findById(assetId);
    }

    @PostMapping("")
    public ApiMessage saveAsset(@RequestBody Asset asset){
        return assetService.save(asset);
    }

    @PutMapping("")
    public ApiMessage updateAsset(@RequestBody Asset asset){
        return assetService.update(asset);
    }

    @DeleteMapping("/{assetId}")
    public ApiMessage deleteAsset(@PathVariable("assetId") int assetId){
        return assetService.delete(assetId);
    }

    @GetMapping("/author/{authorId}")
    public List<Asset> getAssetsByAuthorId(@PathVariable("authorId") int authorId) {
        return assetService.findAssetsByAuthorId(authorId);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Asset> getAssetsByBuyerId(@PathVariable("buyerId") int buyerId) {
        return assetService.findAssetsByBuyerId(buyerId);
    }

    @GetMapping("/sticker/{stickerId}")
    public List<Asset> getAssetsByStickerId(@PathVariable("stickerId") int stickerId) {
        return assetService.findAssetsByStickerId(stickerId);
    }

    @GetMapping("/landmark/{landmark}")
    public List<Asset> getAssetsByLandmarkId(@PathVariable("landmark") Asset.Landmark landmark) {
        return assetService.findAssetsByLandmark(landmark);
    }

    @GetMapping("/theme/{themeId}")
    public List<Asset> getAssetsByAssetId(@PathVariable("themeId") int themeId) {
        return assetService.findAssetsByThemeId(themeId);
    }
}
