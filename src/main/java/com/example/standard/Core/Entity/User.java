package com.example.standard.Core.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String fisrtname;
    @NotBlank
    private String lastname;
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonBackReference
    private Set<Role> roles;

    public User(String username, String password, String fisrtname, String lastname) {
        this.username = username;
        this.password = password;
        this.fisrtname = fisrtname;
        this.lastname = lastname;
    }

    public User() {
    }
}
