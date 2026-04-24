package com.example.postcommentapi.services;

import com.example.postcommentapi.domain.Post;
import com.example.postcommentapi.repository.PostRepository;
import com.example.postcommentapi.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Post user = repo.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("Post not found. Id: " + id);
        }
        return user;
    }

    public List<Post> findByTitle(String text) {
        return repo.searchByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }
}
