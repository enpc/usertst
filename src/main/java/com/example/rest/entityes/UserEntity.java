package com.example.rest.entityes;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEntity {

    @Id
    @GeneratedValue
    private final Long id;

    @Column(unique = true)
    private final String name;

    private final String firstName;

    private final String lastName;

    private final String password;

    private final Boolean active;
}
