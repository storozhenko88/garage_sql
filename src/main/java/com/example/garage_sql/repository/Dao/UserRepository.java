package com.example.garage_sql.repository.Dao;

import com.example.garage_sql.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
}
