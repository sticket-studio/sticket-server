package com.ec.sticket.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @JoinTable(name = "user_title",
            joinColumns = @JoinColumn(name = "title_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id")
    )
    List<User> users = new ArrayList<>();

    private String name;

    public Title(String name) {
        this.name = name;
    }
}
