package com.example.SOCIAL_MEDIA.Services;

import com.example.SOCIAL_MEDIA.DTO.CreatePostRequest;
import com.example.SOCIAL_MEDIA.DTO.PostResponse;
import com.example.SOCIAL_MEDIA.Model.Author;
import com.example.SOCIAL_MEDIA.Model.Post;
import com.example.SOCIAL_MEDIA.Repository.AuthorRepository;
import com.example.SOCIAL_MEDIA.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public PostResponse addPost(CreatePostRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedBy(author);
        post.setCreatedAt(LocalDateTime.now());

        Post saved = postRepository.save(post);

        return toResponse(saved, "Post created successfully");
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> toResponse(post, "Post found"))
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsByAuthor(Long authorId) {
        return postRepository.findByCreatedBy_Id(authorId)
                .stream()
                .map(post -> toResponse(post, "Post found"))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return toResponse(post, "Post found");
    }

    public PostResponse updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(title);
        post.setContent(content);

        Post updated = postRepository.save(post);

        return toResponse(updated, "Post updated successfully");
    }

    public void deletePostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    public void deletePostsByAuthor(Long authorId) {
        postRepository.deleteByCreatedBy_Id(authorId);
    }

    public void deletePostsBetween(LocalDateTime start, LocalDateTime end) {
        postRepository.deleteByCreatedAtBetween(start, end);
    }

    private PostResponse toResponse(Post post, String message) {
        return new PostResponse(
                message,
                post.getTitle(),
                post.getContent(),
                post.getVisibility(),
                post.getCreatedAt(),
                post.getCreatedBy().getFullName()
        );
    }
}