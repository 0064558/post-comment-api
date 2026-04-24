package com.example.postcommentapi.dto;

import com.example.postcommentapi.domain.User;

import java.io.Serializable;

// DTO (Data Transfer Object) é uma classe simples que tem a função de transportar dados entre processos, sem conter lógica de negócio. Ele é usado para evitar expor a estrutura interna do modelo de domínio e para otimizar a transferência de dados, especialmente em casos onde o modelo de domínio é complexo ou contém informações sensíveis.
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;

    public UserDTO() {
    }

    public UserDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
