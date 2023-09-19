package com.CrudApp.Crud_App.controller;
import com.CrudApp.Crud_App.exception.UserNotFoundException;
import com.CrudApp.Crud_App.model.User;
import com.CrudApp.Crud_App.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Posting Data
    @PostMapping("/user")
    User newUser(@RequestBody User newUser)
    {
        return userRepository.save(newUser);
    }

    //Getting Data

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //For Editing The user
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
    return userRepository.findById(id)
            .orElseThrow(()->new UserNotFoundException(id));

    }


    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id ){
        return userRepository.findById(id)
                .map(user->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return  userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }


    //Delete Data

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" Has been Deleted Success...";
    }
}
