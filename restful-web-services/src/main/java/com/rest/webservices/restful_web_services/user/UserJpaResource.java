package com.rest.webservices.restful_web_services.user;

import com.rest.webservices.restful_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserDaoService service;

    private UserRepository repository;

    public UserJpaResource(UserRepository repository){

        this.repository = repository;
    }
    @GetMapping("/jpa/users")
    public List<User> RetriveAllUsers(){
        return repository.findAll();

    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User>  GetUserBasedOnId(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("id : " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get() );

        WebMvcLinkBuilder link = linkTo(methodOn( this.getClass()).RetriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> AddNewUser(@Valid  @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/jpa/users/{id}")
    public void DeleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }

}
