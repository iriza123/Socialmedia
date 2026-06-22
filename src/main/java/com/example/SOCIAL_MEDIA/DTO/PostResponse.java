package com.example.SOCIAL_MEDIA.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private String message;
    private String title;
    private String content;
    private String visibility;
    private LocalDateTime createdAt;
    private String createdBy;
}