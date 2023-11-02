package br.com.freitas.msuser.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.freitas.msuser.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
