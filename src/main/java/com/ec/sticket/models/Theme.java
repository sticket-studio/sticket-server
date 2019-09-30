package com.ec.sticket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Theme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "theme")
    private List<Asset> assets = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "theme")
    private List<Sticon> sticons = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "theme")
    private List<Motionticon> motionticons = new ArrayList<>();

    private String name;
    private int cnt;

    public Theme(String name) {
        this.name = name;
        this.cnt = 0;
    }
}
