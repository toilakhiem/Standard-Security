package com.example.standard.Core.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String rolename;
    private String description;
    @ManyToMany(mappedBy = "roles") @JsonBackReference
    private Set<User> roles;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permisson_id")
    )
    private Set<Permission> permissions;
}
