package com.example.rest.entityes;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ContactEntity {

    @Id
    private Long userId;

    private String city;

    private String address;
}
