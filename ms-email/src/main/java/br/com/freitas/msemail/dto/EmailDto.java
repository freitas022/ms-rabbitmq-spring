package br.com.freitas.msemail.dto;

import java.util.UUID;

public record EmailDto(UUID userId,
						String emailTo,
						String subject,
						String text) {

}
