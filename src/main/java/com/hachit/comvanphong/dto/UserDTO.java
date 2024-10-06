package com.hachit.comvanphong.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Full name is required")
    @Size(max = 200, message = "Full name must be at most 200 characters")
    private String fullName;

    @NotBlank(message = "Office address is required")
    @Size(max = 255, message = "Office address must be at most 255 characters")
    private String officeAddress;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Invalid phone number format")
    @Pattern(regexp = "\\d{9,10}", message = "Invalid phone number format") // Chỉ cho phép số và từ 10-20 ký tự
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotBlank(message = "Building is required")
    @Size(max = 100, message = "Building must be at most 100 characters")
    private String building;

    @NotBlank(message = "Floors is required")
    @Size(max = 100, message = "Floors must be at most 100 characters")
    private String floors;
}
