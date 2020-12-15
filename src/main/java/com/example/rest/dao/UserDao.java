package com.example.rest.dao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.rest.services.User;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class UserDao implements User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Boolean active;
}
