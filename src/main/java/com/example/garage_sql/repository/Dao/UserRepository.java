package com.example.garage_sql.repository.Dao;

import com.example.garage_sql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//@Repository
public interface UserRepository extends CrudRepository <User, Integer> {
  //  @Query("SELECT 'email', 'first_name'FROM "users" WHERE email = ?1")
    User findByEmail(String email);

}