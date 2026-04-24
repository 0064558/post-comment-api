package com.example.postcommentapi.config;

import com.example.postcommentapi.domain.Post;
import com.example.postcommentapi.domain.User;
import com.example.postcommentapi.dto.AuthorDTO;
import com.example.postcommentapi.repository.PostRepository;
import com.example.postcommentapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... arg0) throws Exception {
        repairPostAuthorIds();

        // Seed only when collections are empty.
        if (userRepository.count() > 0 || postRepository.count() > 0) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        List<User> savedUsers = userRepository.saveAll(Arrays.asList(maria, alex, bob));

        User savedMaria = savedUsers.stream()
                .filter(user -> "maria@gmail.com".equals(user.getEmail()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Seed user Maria Brown was not persisted"));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Sao Paulo. Abracos!", new AuthorDTO(savedMaria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(savedMaria));
        postRepository.saveAll(Arrays.asList(post1, post2));
    }

    private void repairPostAuthorIds() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return;
        }

        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            return;
        }

        List<Post> changedPosts = new ArrayList<>();

        for (Post post : posts) {
            AuthorDTO author = post.getAuthor();
            if (author == null || author.getName() == null || author.getName().isBlank()) {
                continue;
            }

            boolean invalidId = author.getId() == null || author.getId().isBlank() || !userRepository.existsById(author.getId());
            if (!invalidId) {
                continue;
            }

            User matchedUser = users.stream()
                    .filter(user -> author.getName().equals(user.getName()))
                    .findFirst()
                    .orElse(null);

            if (matchedUser != null) {
                post.setAuthor(new AuthorDTO(matchedUser));
                changedPosts.add(post);
            }
        }

        if (!changedPosts.isEmpty()) {
            postRepository.saveAll(changedPosts);
        }
    }
}
