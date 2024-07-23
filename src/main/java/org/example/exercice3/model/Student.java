package org.example.exercice3.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private UUID id;

    @NotNull(message = "Le nom doit être renseigné.")
    @NotBlank(message = "Le nom ne peut pas être vide.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+([-\\s'][a-zA-ZÀ-ÿ]+)*$", message = "Le nom ne peut pas contenir uniquement des signes et doit être valide.")
    private String lastname;

    @NotNull(message = "Le prénom doit être renseigné.")
    @NotBlank(message = "Le prénom ne peut pas être vide.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+([-\\s'][a-zA-ZÀ-ÿ]+)*$", message = "Le prénom ne peut pas contenir uniquement des signes et doit être valide.")
    private String firstname;

    @Min(value = 18, message = "L''âge doit être supérieur ou égal à 18.")
    @Max(value = 100, message = "L''âge doit être inférieur ou égal à 100.")
    private int age;

    @NotNull(message = "L''email doit être renseigné.")
    @Email(message = "L''email doit être valide.")
    private String email;
}
