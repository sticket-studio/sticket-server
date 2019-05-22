package com.ec.sticket.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String username;
    private String password;
}
