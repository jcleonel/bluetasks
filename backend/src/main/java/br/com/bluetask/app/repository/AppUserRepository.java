package br.com.bluetask.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.bluetask.app.entities.AppUser;

@Repository
@RepositoryRestResource(exported = false)
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	
	AppUser findByUsername(String username);

}
