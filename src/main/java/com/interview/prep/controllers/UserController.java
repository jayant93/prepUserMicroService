package com.interview.prep.controllers;

import com.interview.prep.exceptions.UserCreationFailedException;
import com.interview.prep.records.UserRecord;
import com.interview.prep.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //@Autowire is optional after spring 4.3 in constructor injection
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserRecord user){
        userService.createUser(user);
        return ResponseEntity.ok("User Creation Successfull");
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping()
    public UserRecord getUserByName(@RequestParam String name){
        return userService.getUserByName(name);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUserDetailsById(@PathVariable Long id,@RequestBody UserRecord user){
        userService.updateUserById(id, user);
        return ResponseEntity.ok().body("User Details Updated Successfully");
    }

    @PutMapping
    public ResponseEntity updateUserDetailsByName(@RequestBody UserRecord user){
        userService.updateUserByName(user);
        return ResponseEntity.ok().body("User Details Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("User Deleted  Successfully");
    }

    @DeleteMapping()
    public ResponseEntity deleteUserByName(@RequestParam String name){
        userService.deleteUserByName(name);
        return ResponseEntity.ok().body("User Deleted  Successfully");
    }


}
