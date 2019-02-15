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
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @OneToMany(mappedBy = "landmark")
    private List<Asset> assets= new ArrayList<>();

    private String name;
}
