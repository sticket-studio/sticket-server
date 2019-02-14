package com.ec.sticket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private Long idx;

    private String id;
    private String pw;
    private String name;
    private String email;
    private String snsType;
    private String token;
    private int stick;
}
