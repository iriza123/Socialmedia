package com.example.SOCIAL_MEDIA.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long i;

    private String fullName;

    @Column(unique = true)
    private String username;

    private String email;

    private String bio;

    private LocalDateTime createdAt;
}
