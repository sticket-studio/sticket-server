package com.ec.sticket.dto.request.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignupRequest {
    private String email;
    private String name;
    private String description;
    private String password;
}
