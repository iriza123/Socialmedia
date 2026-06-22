package com.example.SOCIAL_MEDIA.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAuthorRequest {

    @Size(min = 5, message = "Full name must contain at least 5 characters")
    private String fullName;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Email(message = "Email must be valid")
    private String email;
}
