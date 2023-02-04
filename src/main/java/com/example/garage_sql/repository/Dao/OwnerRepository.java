package com.example.garage_sql.repository.Dao;

import com.example.garage_sql.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {

    List<Owner> findAll();
}
