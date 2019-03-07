package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.services.CashItemService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/cashitem")
public class CashItemController {
    private final UserService userService;
    private final CashItemService cashItemService;

    public CashItemController(UserService userService, CashItemService cashItemService) {
        this.userService = userService;
        this.cashItemService = cashItemService;
    }

    @GetMapping("")
    public List<CashItem> findAllCashItems(){
        return cashItemService.findAll();
    }

    @GetMapping("/{cashItemId}")
    public CashItem findCashItemById(@PathVariable("cashItemId") int cashItemId){
        return cashItemService.findById(cashItemId);
    }

    @PostMapping("")
    public ApiMessage saveCashItem(@RequestBody CashItem cashItem){
        return cashItemService.save(cashItem);
    }

    @PutMapping("")
    public ApiMessage updateCashItem(@RequestBody CashItem cashItem){
        return cashItemService.update(cashItem);
    }

    @DeleteMapping("/{cashItemId}")
    public ApiMessage deleteCashItem(@PathVariable("cashItemId") int cashItemId){
        return cashItemService.delete(cashItemId);
    }
}
