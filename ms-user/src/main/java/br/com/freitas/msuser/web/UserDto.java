package br.com.freitas.msuser.web;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(UUID userId, 
					  @NotBlank String name,
					  @NotBlank @Email String email,
					  @NotBlank String password) {

}
