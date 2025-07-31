package com.rest.webservices.restful_web_services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> RetriveAllUsers(){
        return service.findAll();

    }

    @GetMapping("/users/{id}")
    public User GetUserBasedOnId(@PathVariable Integer id){
        User user = service.findUserBasedOnId(id);
        if (user==null){
            throw new UserNotFoundException("id : " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> AddNewUser(@RequestBody User user){
        User savedUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
