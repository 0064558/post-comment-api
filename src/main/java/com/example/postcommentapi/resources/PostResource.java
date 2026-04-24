package com.example.postcommentapi.resources;

import com.example.postcommentapi.domain.Post;
import com.example.postcommentapi.resources.util.URL;
import com.example.postcommentapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value="/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(name = "text", defaultValue = "") String text) {
        List<Post> list = service.findByTitle(URL.decodeParam(text));
        return ResponseEntity.ok().body(list);
    }
}
