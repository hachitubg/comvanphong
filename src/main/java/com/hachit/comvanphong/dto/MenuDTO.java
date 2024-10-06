package com.hachit.comvanphong.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuDTO {
    private Long id;

    @NotBlank(message = "Menu name is required.")
    @Size(max = 255, message = "Menu name must be less than 255 characters.")
    private String nameMenu;

    private String detail;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid.")
    private BigDecimal price;

    private char delYn;
}
