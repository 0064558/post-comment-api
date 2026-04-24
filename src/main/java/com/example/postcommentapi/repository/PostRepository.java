package com.example.postcommentapi.repository;

import com.example.postcommentapi.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

// Interface de acesso a dados PostRepository, responsável por realizar operações de CRUD (Create, Read, Update, Delete) na coleção de posts do MongoDB. Ela estende a interface MongoRepository, que fornece métodos prontos para manipulação dos dados, como salvar, buscar por ID, deletar, etc. O tipo de entidade é Post e o tipo do ID é String.
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchByTitle(String text);

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
