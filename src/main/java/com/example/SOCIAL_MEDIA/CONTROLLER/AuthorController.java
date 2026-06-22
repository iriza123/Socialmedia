package com.example.SOCIAL_MEDIA.CONTROLLER;

import com.example.SOCIAL_MEDIA.DTO.AuthorResponse;
import com.example.SOCIAL_MEDIA.DTO.CreateAuthorRequest;
import com.example.SOCIAL_MEDIA.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public AuthorResponse addAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        return authorService.addAuthor(request);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/username/{username}")
    public AuthorResponse getAuthorByUsername(@PathVariable String username) {
        return authorService.getAuthorByUsername(username);
    }

    @GetMapping("/email/{email}")
    public AuthorResponse getAuthorByEmail(@PathVariable String email) {
        return authorService.getAuthorByEmail(email);
    }

    @GetMapping
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/created-between")
    public List<AuthorResponse> getAuthorsCreatedBetween(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return authorService.getAuthorsCreatedBetween(start, end);
    }

    @PutMapping("/{id}/bio")
    public AuthorResponse updateBio(@PathVariable Long id, @RequestBody String bio) {
        return authorService.updateBio(id, bio);
    }

    @PutMapping("/{id}/profile")
    public AuthorResponse updateProfile(
            @PathVariable Long id,
            @RequestParam String fullName,
            @RequestParam String username,
            @RequestParam String email) {
        return authorService.updateProfile(id, fullName, username, email);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}