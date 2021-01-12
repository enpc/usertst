package com.example.rest.entityes;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {

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
