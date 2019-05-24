package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Asset;
import com.ec.sticket.models.mapping.UserAssetPurchase;
import com.ec.sticket.services.AssetService;
import com.ec.sticket.util.ApiMessage;
import com.ec.sticket.util.JwtParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/normal/assets")
@Api(value = "AssetController", description = "에셋 컨트롤러")
public class AssetController {

    private final AssetService assetService;
    private final JwtParser jwtParser;

    public AssetController(AssetService assetService, JwtParser jwtParser) {
        this.assetService = assetService;
        this.jwtParser = jwtParser;
    }

    @GetMapping("")
    @ApiOperation(value = "모든 에셋 조회", notes = "모든 Asset 리스트를 반환")
    public List<Asset> findAllAssets() {
        return assetService.findAll();
    }

    //TODO: 인기 에셋 조회 구현
    @GetMapping("/today")
    @ApiOperation(value = "오늘의 에셋 조회", notes = "오늘의 Asset 리스트를 반환(미구현)")
    public List<Asset> findTodayAssets() {
        return assetService.findAll();
    }

    //TODO: 인기 에셋 조회 구현
    @GetMapping("/popular")
    @ApiOperation(value = "인기 에셋 조회", notes = "인기 있는 Asset 리스트를 반환(미구현)")
    public List<Asset> findPopularAssets() {
        return assetService.findAll();
    }

    @GetMapping("/{assetId}")
    @ApiOperation(value = "ID를 통해 에셋 조회", notes = "Asset을 반환")
    public Asset findAssetById(
            @ApiParam(value = "에셋 ID", defaultValue = "1", required = true)
            @PathVariable("assetId") int assetId) {
        return assetService.findById(assetId);
    }

    @PostMapping("/{authorId}")
    @ApiOperation(value = "에셋 저장하기", notes = "Asset 저장")
    @ApiImplicitParam(name = "asset", value = "생성하는 에셋 내용", required = true,  paramType= "body")
    public ApiMessage saveAsset(
            @ApiParam(value = "저자 ID", defaultValue = "1", required = true)
            @PathVariable("authorId") int authorId,
            @RequestBody Asset asset) {
        return assetService.save(authorId, asset);
    }

    @PutMapping("")
    @ApiOperation(value = "에셋 수정", notes = "Asset 수정")
    public ApiMessage updateAsset(@RequestBody Asset asset) {
        return assetService.update(asset);
    }

    @DeleteMapping("/{assetId}")
    @ApiOperation(value = "에셋 삭제하기", notes = "Asset 삭제")
    public ApiMessage deleteAsset(
            @ApiParam(value = "삭제할 에셋 ID", defaultValue = "1", required = true)
            @PathVariable("assetId") int assetId) {
        return assetService.delete(assetId);
    }

    @GetMapping("/free")
    @ApiOperation(value = "무료 에셋 찾기", notes = "무료 에셋 찾기")
    public List<Asset> getFreeAssets() {
        return assetService.findFreeAssets();
    }

    @GetMapping("/author/{authorId}")
    @ApiOperation(value = "에셋 찾기 : AuthorId", notes = "Author ID로 Asset 찾기")
    public List<Asset> getAssetsByAuthorId(
            @ApiParam(value = "찾을 에셋의 저자 ID", defaultValue = "1", required = true)
            @PathVariable("authorId") int authorId) {
        return assetService.findAssetsByAuthorId(authorId);
    }

    @GetMapping("/buyer")
    @ApiOperation(value = "에셋 찾기 : buyerId", notes = "Buyer ID로 Asset 찾기")
    public List<Asset> getAssetsByBuyerId(Authentication authentication) {
        return jwtParser.getUserFromJwt(authentication).getUserAssetPurchases()
                .stream().map(UserAssetPurchase::getAsset).collect(Collectors.toList());
    }

    @GetMapping("/sticon/{sticonId}")
    @ApiOperation(value = "에셋 찾기 : sticonId", notes = "Sticon ID로 Asset 찾기")
    public List<Asset> getAssetsBySticonId(
            @ApiParam(value = "찾을 에셋의 스티커 ID", defaultValue = "1", required = true)
            @PathVariable("sticonId") int sticonId) {
        return assetService.findAssetsBySticonId(sticonId);
    }

    @GetMapping("/landmark/{landmark}")
    @ApiOperation(value = "에셋 찾기 : landmarkId", notes = "Landmark ID로 Asset 찾기")
    public List<Asset> getAssetsByLandmarkId(
            @ApiParam(value = "찾을 에셋의 랜드마크 ID", defaultValue = "EYE_LEFT", required = true)
            @PathVariable("landmark") Asset.Landmark landmark) {
        return assetService.findAssetsByLandmark(landmark);
    }

    @GetMapping("/theme/{themeId}")
    @ApiOperation(value = "에셋 찾기 : sticonId", notes = "Asset ID로 Asset 찾기")
    public List<Asset> getAssetsByAssetId(
            @ApiParam(value = "찾을 에셋의 테마 ID", defaultValue = "1", required = true)
            @PathVariable("themeId") int themeId) {
        return assetService.findAssetsByThemeId(themeId);
    }
}
