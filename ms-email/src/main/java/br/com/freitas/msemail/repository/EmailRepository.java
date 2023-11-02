package br.com.freitas.msemail.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.freitas.msemail.domain.Email;

public interface EmailRepository extends JpaRepository<Email, UUID> {

}
