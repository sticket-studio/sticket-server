package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.services.CashItemService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/cashitem")
@Api(value = "CashItemController", description = "캐시아이템 컨트롤러")
public class CashItemController {
    private final UserService userService;
    private final CashItemService cashItemService;

    public CashItemController(UserService userService, CashItemService cashItemService) {
        this.userService = userService;
        this.cashItemService = cashItemService;
    }

    @GetMapping("")
    @ApiOperation(value = "캐시아이템 찾기", notes = "모든 캐시아이템 찾기")
    public List<CashItem> findAllCashItems(){
        return cashItemService.findAll();
    }

    @GetMapping("/{cashItemId}")
    @ApiOperation(value = "캐시아이템 찾기 : 캐시아이템 ID", notes = "캐시아이템 ID로 캐시아이템 찾기")
    public CashItem findCashItemById(@PathVariable("cashItemId") int cashItemId){
        return cashItemService.findById(cashItemId);
    }

    @PostMapping("")
    @ApiOperation(value = "캐시아이템 저장", notes = "캐시아이템 저장")
    public ApiMessage saveCashItem(@RequestBody CashItem cashItem){
        return cashItemService.save(cashItem);
    }

    @PutMapping("")
    @ApiOperation(value = "캐시아이템 수정", notes = "캐시아이템 수정")
    public ApiMessage updateCashItem(@RequestBody CashItem cashItem){
        return cashItemService.update(cashItem);
    }

    @DeleteMapping("/{cashItemId}")
    @ApiOperation(value = "캐시아이템 삭제 : 캐시아이템 ID", notes = "캐시아이템 ID로 캐시아이템 삭제")
    public ApiMessage deleteCashItem(@PathVariable("cashItemId") int cashItemId){
        return cashItemService.delete(cashItemId);
    }
}
