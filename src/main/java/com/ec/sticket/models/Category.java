package com.ec.sticket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idx;

    @OneToMany(mappedBy = "category_idx")
    private List<Sticker> stickers = new ArrayList<>();

    @OneToMany(mappedBy = "category_idx")
    private List<Asset> assets = new ArrayList<>();


    private String name;
}
