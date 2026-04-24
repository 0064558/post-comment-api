package com.example.postcommentapi.services;

import com.example.postcommentapi.domain.User;
import com.example.postcommentapi.dto.UserDTO;
import com.example.postcommentapi.repository.UserRepository;
import com.example.postcommentapi.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        User user = repo.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found. Id: " + id);
        }
        return user;
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    // Método para converter um UserDTO em User
    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
