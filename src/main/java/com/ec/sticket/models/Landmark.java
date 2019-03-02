package com.ec.sticket.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "landmark")
    private List<Asset> assets= new ArrayList<>();

    private String name;

    public Landmark(String name) {
        this.name = name;
    }
}
