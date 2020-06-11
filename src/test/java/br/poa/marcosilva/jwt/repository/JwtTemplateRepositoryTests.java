package br.poa.marcosilva.jwt.repository;

import br.poa.marcosilva.jwt.models.ERole;
import br.poa.marcosilva.jwt.models.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class JwtTemplateRepositoryTests {

	@Autowired
	RoleRepository roleRepository;

	@Test
	void countAllERoles() {
		assertEquals(3, roleRepository.count());
	}

}
