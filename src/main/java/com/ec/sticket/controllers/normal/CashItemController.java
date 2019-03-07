package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.services.CashItemService;
import com.ec.sticket.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("/{cashItemId}/user")
//    public CashItem findAllPurchasedUsersById(@PathVariable("cashItemId") int cashItemId){
//        return cashItemService.findById(cashItemId).getUserCashItemPurchaseList();
//    }
}
