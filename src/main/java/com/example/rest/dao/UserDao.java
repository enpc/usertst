package com.example.rest.dao;

import lombok.*;

import javax.persistence.*;

import com.example.rest.services.User;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDao implements User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private Boolean active;
}
