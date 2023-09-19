package com.CrudApp.Crud_App.repository;

import com.CrudApp.Crud_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository  <User, Long>{
}
