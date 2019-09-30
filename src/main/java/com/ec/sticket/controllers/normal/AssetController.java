package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.Asset;
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

    @GetMapping
    @ApiOperation(value = "에셋 검색", notes = "authorId, buyerId, sticonId, landmark, themeId")
    public List<Asset> getAssets(
            @ApiParam(value = "찾을 에셋의 저자 ID", defaultValue = "1")
            @RequestParam(value = "authorId", required = false, defaultValue = "-1") int authorId,
            @ApiParam(value = "찾을 에셋의 구매자 ID", defaultValue = "1")
            @RequestParam(value = "buyerId", required = false, defaultValue = "-1") int buyerId,
            @ApiParam(value = "찾을 에셋의 Sticon ID", defaultValue = "1")
            @RequestParam(value = "sticonId", required = false, defaultValue = "-1") int sticonId,
            @ApiParam(value = "찾을 에셋의 Landmark")
            @RequestParam(value = "landmark", required = false, defaultValue = "") String landmark,
            @ApiParam(value = "찾을 에셋의 Theme ID", defaultValue = "1")
            @RequestParam(value = "themeId", required = false, defaultValue = "-1") int themeId
    ) {
        return assetService.findAssetsByQuery(authorId, buyerId, sticonId, landmark, themeId);
    }

    @GetMapping("/today")
    @ApiOperation(value = "오늘의 에셋 조회", notes = "오늘의 Asset 리스트를 반환(미구현)")
    public List<Asset> findTodayAssets() {
        return assetService.findTodayAssets();
    }

    @GetMapping("/popular")
    @ApiOperation(value = "인기 에셋 조회", notes = "인기 있는 Asset 리스트를 반환(미구현)")
    public List<Asset> findPopularAssets() {
        return assetService.findPopularAssets();
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
    @ApiImplicitParam(name = "asset", value = "생성하는 에셋 내용", required = true, paramType = "body")
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

    @PostMapping("/{assetId}/like")
    @ApiOperation(value = "에셋 좋아요", notes = "Asset 좋아요")
    @ApiImplicitParam(name = "asset", value = "에셋 좋아요", required = true, paramType = "body")
    public ApiMessage likeAsset(@PathVariable("assetId") int assetId, Authentication authentication) {
        return assetService.like(jwtParser.getUserIdFromJwt(authentication), assetId);
    }

    @GetMapping("/like")
    @ApiOperation(value = "내가 좋아요한 에셋 조회", notes = "내가 좋아요한 에셋 조회")
    @ApiImplicitParam(name = "asset", value = "내가 좋아요한 에셋 조회", required = true, paramType = "body")
    public List<Asset> likeAsset(Authentication authentication) {
        return assetService.like(jwtParser.getUserFromJwt(authentication));
    }
}
