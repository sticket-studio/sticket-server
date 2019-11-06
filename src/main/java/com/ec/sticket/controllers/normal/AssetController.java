package com.ec.sticket.controllers.normal;

import com.ec.sticket.dto.request.asset.InsertAssetRequest;
import com.ec.sticket.dto.response.asset.SimpleAssetResponse;
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

    @GetMapping
    @ApiOperation(value = "에셋 검색", notes = "authorId, buyerId, landmark, themeId")
    public List<SimpleAssetResponse> getAssets(
            @ApiParam(value = "찾을 에셋의 저자 ID", defaultValue = "1")
            @RequestParam(value = "authorId", required = false, defaultValue = "0") int authorId,
            @ApiParam(value = "찾을 에셋의 구매자 ID", defaultValue = "1")
            @RequestParam(value = "buyerId", required = false, defaultValue = "0") int buyerId,
            @ApiParam(value = "찾을 에셋의 Landmark", required = false, defaultValue = "EYE_LEFT")
            @RequestParam(value = "landmark", required = false, defaultValue = "") String landmark,
            @ApiParam(value = "찾을 에셋의 Theme ID", defaultValue = "1")
            @RequestParam(value = "themeId", required = false, defaultValue = "0") int themeId
    ) {
        return assetService.findAssetsByQuery(authorId, buyerId, landmark, themeId)
                .stream().map(SimpleAssetResponse::mapping).collect(Collectors.toList());
    }

    @GetMapping("/today")
    @ApiOperation(value = "오늘의 에셋 조회", notes = "오늘의 Asset 리스트를 반환")
    public List<SimpleAssetResponse> findTodayAssets(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        return assetService.findTodayAssets(--page)
                .stream().map(SimpleAssetResponse::mapping).collect(Collectors.toList());
    }

    @GetMapping("/popular")
    @ApiOperation(value = "인기 에셋 조회", notes = "인기 있는 Asset 리스트를 반환")
    public List<SimpleAssetResponse> findPopularAssets(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        return assetService.findPopularAssets(--page)
                .stream().map(SimpleAssetResponse::mapping).collect(Collectors.toList());
    }

    @GetMapping("/new")
    @ApiOperation(value = "신규 에셋 조회", notes = "신규 Asset 리스트를 반환")
    public List<SimpleAssetResponse> findNewAssets(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        return assetService.findNewAssets(--page)
                .stream().map(SimpleAssetResponse::mapping).collect(Collectors.toList());
    }

    @GetMapping("/{assetId}")
    @ApiOperation(value = "ID를 통해 에셋 조회", notes = "Asset을 반환")
    public Asset findAssetById(
            @ApiParam(value = "에셋 ID", defaultValue = "1", required = true)
            @PathVariable("assetId") int assetId) {
        return assetService.findById(assetId);
    }

    @PostMapping
    @ApiOperation(value = "에셋 등록하기", notes = "Asset 등록")
    @ApiImplicitParam(name = "asset", value = "생성하는 에셋 내용", required = true, paramType = "body")
    public ApiMessage saveAsset(@ModelAttribute InsertAssetRequest model, Authentication authentication) {
        return assetService.save(jwtParser.getUserIdFromJwt(authentication), model);
    }

    @PutMapping("/{assetId}")
    @ApiOperation(value = "에셋 수정", notes = "Asset 수정")
    public ApiMessage updateAsset(@PathVariable int assetId, @RequestBody Asset asset) {
        return assetService.update(assetId, asset);
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
    public ApiMessage likeAsset(@PathVariable("assetId") int assetId, Authentication authentication) {
        return assetService.like(jwtParser.getUserFromJwt(authentication), assetId);
    }

    @PostMapping("/{assetId}/dislike")
    @ApiOperation(value = "에셋 좋아요 취소", notes = "Asset 좋아요 취소")
    public ApiMessage dislikeAsset(@PathVariable("assetId") int assetId, Authentication authentication) {
        return assetService.dislike(jwtParser.getUserFromJwt(authentication), assetId);
    }

    @GetMapping("/{assetId}/like")
    @ApiOperation(value = "에셋 좋아요 여부 확인", notes = "Asset 좋아요 여부 확인")
    public ApiMessage checkLike(@PathVariable("assetId") int assetId, Authentication authentication) {
        return assetService.checkLike(jwtParser.getUserFromJwt(authentication), assetId);
    }

    @GetMapping("/like")
    @ApiOperation(value = "내가 좋아요한 에셋 조회", notes = "내가 좋아요한 에셋 조회")
    public List<SimpleAssetResponse> getLikeList(Authentication authentication) {
        return assetService.getUsersLikeAssets(jwtParser.getUserFromJwt(authentication))
                .stream().map(SimpleAssetResponse::mapping).collect(Collectors.toList());
    }

    @PostMapping("/{assetId}/purchase")
    @ApiOperation(value = "에셋 구매", notes = "Asset 구매")
    public ApiMessage purchaseAsset(@PathVariable("assetId") int assetId, Authentication authentication) {
        return assetService.purchaseAsset(jwtParser.getUserFromJwt(authentication), assetId);
    }

    @GetMapping("/{assetId}/purchase")
    @ApiOperation(value = "에셋 구매 여부 확인", notes = "Asset 구매 여부 확인")
    public ApiMessage checkPurchase(@PathVariable("assetId") int assetId, Authentication authentication) {
        return assetService.checkPurchase(jwtParser.getUserFromJwt(authentication), assetId);
    }

    @GetMapping("/purchase")
    @ApiOperation(value = "내가 구매한한 에셋 조회", notes = "내가 구매한 에셋 조회")
    @ApiImplicitParam(name = "asset", value = "내가 구매한 에셋 조회", required = true, paramType = "body")
    public List<SimpleAssetResponse> getBoughtList(Authentication authentication) {
        return assetService.getUsersBoughtAssets(jwtParser.getUserFromJwt(authentication))
                .stream().map(SimpleAssetResponse::mapping).collect(Collectors.toList());
    }
}
