package com.example.postcommentapi.repository;

import com.example.postcommentapi.domain.Post;
import com.example.postcommentapi.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

// Interface de acesso a dados PostRepository, responsável por realizar operações de CRUD (Create, Read, Update, Delete) na coleção de posts do MongoDB. Ela estende a interface MongoRepository, que fornece métodos prontos para manipulação dos dados, como salvar, buscar por ID, deletar, etc. O tipo de entidade é Post e o tipo do ID é String.
public interface PostRepository extends MongoRepository<Post, String> {

}
