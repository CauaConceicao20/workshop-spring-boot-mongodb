package com.cauaconceicao.workshopmongo.resources;

import com.cauaconceicao.workshopmongo.domain.Post;
import com.cauaconceicao.workshopmongo.domain.User;
import com.cauaconceicao.workshopmongo.dto.UserDTO;
import com.cauaconceicao.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/lista")
    public ResponseEntity<List<UserDTO>> findAll() {

        List<User> list = userService.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/buscaPorId/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);

        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping("/insercaoUser")
    @Transactional
    public ResponseEntity<Void> insertUsers(@RequestBody UserDTO userDto) {
        User user = userService.fromDTO(userDto);
        userService.insertUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/atualizaUser/{id}")
    @Transactional
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/deletaUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/retornaPosts/{id}")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }


}
