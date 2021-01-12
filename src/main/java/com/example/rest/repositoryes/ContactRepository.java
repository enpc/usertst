package com.example.rest.repositoryes;

import com.example.rest.entityes.ContactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long> {
}
