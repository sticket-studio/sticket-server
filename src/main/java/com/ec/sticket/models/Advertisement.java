package com.ec.sticket.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String url;
    private int stick;
    private String imgUrl;
    private String title;
    private String content;
}
