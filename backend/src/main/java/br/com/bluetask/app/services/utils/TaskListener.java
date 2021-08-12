package br.com.bluetask.app.services.utils;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.bluetask.app.entities.AppUser;
import br.com.bluetask.app.entities.Task;
import br.com.bluetask.app.repository.AppUserRepository;

@Component
public class TaskListener {

	private static AppUserRepository appUserRepository;
	
	@Autowired
	public void init(AppUserRepository appUserRepository) {
		TaskListener.appUserRepository = appUserRepository;
	}
	
	@PrePersist
	public void onPersistHandler(Task task) {
		if (task.getAppUser() == null) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			AppUser appUser = appUserRepository.findByUsername(username);
			
			if (appUser == null) {
				throw new EntityNotFoundException("O usuário " + username + " não foi encontrado!");
			}
			
			task.setAppUser(appUser);
		}
	}
	
}
