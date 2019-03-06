package com.ec.sticket.controllers.normal;

import com.ec.sticket.models.CashItem;
import com.ec.sticket.models.User;
import com.ec.sticket.services.CashItemService;
import com.ec.sticket.services.UserService;
import com.ec.sticket.util.ApiMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal/user")
public class UserController {
    private final UserService userService;
    private final CashItemService cashItemService;

    public UserController(UserService userService, CashItemService cashItemService) {
        this.userService = userService;
        this.cashItemService = cashItemService;
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @PostMapping("/")
    public ApiMessage saveUser(@RequestBody User user) {
        if (user != null) {
            userService.saveUser(user);
            return new ApiMessage(ApiMessage.Status.SUCCESS);
        } else {
            return new ApiMessage(ApiMessage.Status.FAIL);
        }
    }

    @GetMapping("/cashitems")
    public List<CashItem> findAllCashItems() {
        return cashItemService.findAll();
    }
}
