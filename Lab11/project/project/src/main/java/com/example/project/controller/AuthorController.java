package com.example.project.controller;

import com.example.project.model.Author;
import com.example.project.repository.AuthorsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorsRepository authorRepository;

    public AuthorController(AuthorsRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}

