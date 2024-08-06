package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;
    @NotEmpty(message = "Required Field Empty : name")
    @Size(min = 3, max = 20, message = "Please enter a name greater than 3 characters and less than 10 characters")
    private String name;

    @NotEmpty(message = "Required Field Missing: Email")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "Required Field Missing: Age")
    @Min(value = 21, message = "Please enter an age greater than 21")
    @Max(value = 80, message = "Please enter an age less than 80")
    @PrimeNumberValidation(message = "You should be in your prime age!!")
    private Integer age;

    @NotEmpty(message = "Required Field Missing: Role")
    @EmployeeRoleValidation(message = "Employee Role INVALID")
    private String role;

    @NotNull(message = "Required Field Missing: Date of Joining")
    @PastOrPresent(message = "Invalid Value: Date Of Joining")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;
}
