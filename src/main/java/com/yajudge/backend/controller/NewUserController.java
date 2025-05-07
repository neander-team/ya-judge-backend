package com.yajudge.backend.controller;

import com.yajudge.backend.dto.NewUserDto;
import com.yajudge.backend.model.NewUser;
import com.yajudge.backend.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class NewUserController {

    private final NewUserService newUserService;

    @Autowired
    public NewUserController(NewUserService newUserService) {
        this.newUserService = newUserService;
    }

    @GetMapping
    public ResponseEntity<List<NewUser>> getAllUsers() {
        List<NewUser> users = newUserService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewUser> getUserById(@PathVariable Long id) {
        return newUserService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<NewUser> createUser(@RequestBody NewUserDto newUserDto) {
        NewUser newUser = newUserDto.toEntity();
        NewUser createdUser = newUserService.createUser(newUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewUser> updateUser(@PathVariable Long id, @RequestBody NewUserDto userDetailsDto) {
        NewUser userDetails = userDetailsDto.toEntity();
        NewUser updatedUser = newUserService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        newUserService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
} 