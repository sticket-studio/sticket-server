package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Asset;
import com.ec.sticket.services.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/normal/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{assetid}")
    public Asset getAssetByid(@PathVariable("assetid") int assetid) {
        return assetService.getAssetByid(assetid);
    }

    @GetMapping("buyer/{buyerid}")
    public List<Asset> getAssetsByBuyerid(@PathVariable("buyerid") int buyerId) {
        return assetService.getAssetsByBuyerId(buyerId);
    }
}
