package com.ec.sticket.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String email;
    private String name;
    private String imgUrl;
    private String description;
}
