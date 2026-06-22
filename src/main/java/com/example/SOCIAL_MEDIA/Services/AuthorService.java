package com.example.SOCIAL_MEDIA.Services;

import com.example.SOCIAL_MEDIA.DTO.AuthorResponse;
import com.example.SOCIAL_MEDIA.DTO.CreateAuthorRequest;
import com.example.SOCIAL_MEDIA.Model.Author;
import com.example.SOCIAL_MEDIA.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorResponse addAuthor(CreateAuthorRequest request) {
        if (authorRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        Author author = new Author();
        author.setFullName(request.getFullName());
        author.setUsername(request.getUsername());
        author.setEmail(request.getEmail());
        author.setCreatedAt(LocalDateTime.now());

        Author saved = authorRepository.save(author);

        return toResponse(saved, "Author created successfully");
    }

    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return toResponse(author, "Author found");
    }

    public AuthorResponse getAuthorByUsername(String username) {
        Author author = authorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return toResponse(author, "Author found");
    }

    public AuthorResponse getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return toResponse(author, "Author found");
    }

    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> toResponse(author, "Author found"))
                .collect(Collectors.toList());
    }

    public List<AuthorResponse> getAuthorsCreatedBetween(LocalDateTime start, LocalDateTime end) {
        return authorRepository.findByCreatedAtBetween(start, end)
                .stream()
                .map(author -> toResponse(author, "Author found"))
                .collect(Collectors.toList());
    }

    public AuthorResponse updateBio(Long id, String bio) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setBio(bio);
        Author updated = authorRepository.save(author);

        return toResponse(updated, "Bio updated successfully");
    }

    public AuthorResponse updateProfile(Long id, String fullName, String username, String email) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        if (!author.getUsername().equals(username) && authorRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        author.setFullName(fullName);
        author.setUsername(username);
        author.setEmail(email);

        Author updated = authorRepository.save(author);

        return toResponse(updated, "Profile updated successfully");
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
    }

    private AuthorResponse toResponse(Author author, String message) {
        return new AuthorResponse(
                message,
                author.getFullName(),
                author.getUsername(),
                author.getEmail(),
                author.getCreatedAt()
        );
    }
}