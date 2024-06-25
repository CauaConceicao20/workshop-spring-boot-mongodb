package com.cauaconceicao.workshopmongo.service;

import com.cauaconceicao.workshopmongo.domain.User;
import com.cauaconceicao.workshopmongo.dto.UserDTO;
import com.cauaconceicao.workshopmongo.repository.UserRepository;
import com.cauaconceicao.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
    Optional<User> user = userRepository.findById(id);

    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insertUser(User user) {
        return userRepository.insert(user);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
