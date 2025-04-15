package com.example.manager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName){

        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestParam User user){
        User createUser = userService.addUser(user);

        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User resultUser = userService.updateUser(user);
        if(resultUser != null){
            return new ResponseEntity<>(resultUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
       userService.deleteUser(userId);
       return new ResponseEntity<>("User deleted!",HttpStatus.OK);
    }
}
