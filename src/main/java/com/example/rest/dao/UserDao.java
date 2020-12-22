package com.example.rest.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDao{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String firstName;

    private String lastName;

    private String password;

    private Boolean active;
}
