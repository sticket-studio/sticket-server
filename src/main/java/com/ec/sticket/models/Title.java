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
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToMany
    @JoinTable(name = "user_title",
            joinColumns = @JoinColumn(name = "title_idx",
                    referencedColumnName = "idx"),
            inverseJoinColumns = @JoinColumn(name = "user_idx",
                    referencedColumnName = "idx")
    )
    List<User> users = new ArrayList<>();

    private String name;

}
