package com.example.SOCIAL_MEDIA.CONTROLLER;

import com.example.SOCIAL_MEDIA.DTO.CreatePostRequest;
import com.example.SOCIAL_MEDIA.DTO.PostResponse;
import com.example.SOCIAL_MEDIA.Services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public PostResponse addPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.addPost(request);
    }

    @GetMapping
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/author/{authorId}")
    public List<PostResponse> getPostsByAuthor(@PathVariable Long authorId) {
        return postService.getPostsByAuthor(authorId);
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content) {
        return postService.updatePost(id, title, content);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id) {
        postService.deletePostById(id);
    }

    @DeleteMapping("/author/{authorId}")
    public void deletePostsByAuthor(@PathVariable Long authorId) {
        postService.deletePostsByAuthor(authorId);
    }

    @DeleteMapping("/created-between")
    public void deletePostsBetween(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        postService.deletePostsBetween(start, end);
    }
}