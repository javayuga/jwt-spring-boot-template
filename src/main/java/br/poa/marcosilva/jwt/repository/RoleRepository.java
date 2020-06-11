package br.poa.marcosilva.jwt.repository;

import java.util.Optional;

import br.poa.marcosilva.jwt.models.ERole;
import br.poa.marcosilva.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
