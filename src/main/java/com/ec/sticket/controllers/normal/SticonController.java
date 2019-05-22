package com.ec.sticket.controllers.normal;

import com.ec.sticket.dto.request.sticon.SticonLikeRequest;
import com.ec.sticket.models.Sticon;
import com.ec.sticket.services.SticonService;
import com.ec.sticket.util.ApiMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/sticons")
@Api(value = "SticonController", description = "스티콘 컨트롤러")
public class SticonController {

    private final SticonService sticonService;

    public SticonController(SticonService sticonService) {
        this.sticonService = sticonService;
    }

    @GetMapping("")
    public List<Sticon> findAllSticon(){
        return sticonService.findAll();
    }

    @GetMapping("/{sticonId}")
    public Sticon findSticonById(@PathVariable("sticonId") int sticonId){
        return sticonService.findById(sticonId);
    }

    @PostMapping("{authorId}")
    public ApiMessage saveSticon(@PathVariable("authorId") int authorId, @RequestBody Sticon sticon){
        return sticonService.save(authorId, sticon);
    }

    @PutMapping("")
    public ApiMessage updateSticon(@RequestBody Sticon sticon){
        return sticonService.update(sticon);
    }

    @DeleteMapping("/{sticonId}")
    public ApiMessage deleteSticon(@PathVariable("sticonId") int sticonId){
        return sticonService.delete(sticonId);
    }

    //TODO: 미구현
    @PostMapping("/like")
    @ApiOperation(value = "스티콘 좋아요", notes = "Sticon 좋아요")
    @ApiImplicitParam(name = "sticon", value = "스티콘 좋아요", required = true,  paramType= "body")
    public ApiMessage deleteAsset(@RequestBody SticonLikeRequest request) {
//        return sticonService.like(request);
        return null;
    }

    @GetMapping("/author/{authorId}")
    public List<Sticon> getSticonsByAuthorId(@PathVariable("authorId") int authorId) {
        return sticonService.getSticonsByAuthorId(authorId);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Sticon> getSticonsByBuyerId(@PathVariable("buyerId") int buyerId) {
        return sticonService.getSticonsByBuyerId(buyerId);
    }

    @GetMapping("/asset/{assetId}")
    public List<Sticon> getSticonsByAssetId(@PathVariable("assetId") int assetId) {
        return sticonService.getSticonsByAssetId(assetId);
    }

    @GetMapping("/motionticon/{motionticonId}")
    public List<Sticon> getSticonsByMotionticonId(@PathVariable("motionticonId") int motionticonId) {
        return sticonService.getSticonsByMotionticonId(motionticonId);
    }

    @GetMapping("/theme/{themeId}")
    public List<Sticon> getSticonsByThemeId(@PathVariable("themeId") int themeId) {
        return sticonService.getSticonsByThemeId(themeId);
    }
}
