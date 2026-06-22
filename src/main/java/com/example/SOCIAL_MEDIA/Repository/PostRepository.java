package com.example.SOCIAL_MEDIA.Repository;

import com.example.SOCIAL_MEDIA.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCreatedBy_Id(Long authorId);

    List<Post> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    void deleteByCreatedBy_Id(Long authorId);

    void deleteByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}