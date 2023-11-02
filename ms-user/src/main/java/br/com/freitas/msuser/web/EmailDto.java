package br.com.freitas.msuser.web;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailDto {
	
	private UUID userId;
	private String emailTo;
	private String subject;
	private String text;

}
