package br.com.bluetask.app.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.bluetask.app.domain.task.Task;
import br.com.bluetask.app.domain.task.TaskRepository;
import br.com.bluetask.app.domain.user.AppUser;
import br.com.bluetask.app.domain.user.AppUserRepository;

@Component
public class InsertTestData {

	private TaskRepository taskRepository;
	private AppUserRepository appUserRepository;
	
	@Autowired
	public InsertTestData(TaskRepository taskRepository, AppUserRepository appUserRepository) {
		this.taskRepository = taskRepository;
		this.appUserRepository = appUserRepository;
	}
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		AppUser appUser = new AppUser("jonh", encoder.encode("abc"), "John Coder");
		appUserRepository.save(appUser);
		
		LocalDate baseDate = LocalDate.parse("2025-02-01");
		
		for (int i = 1; i <= 10; i++) {
			Task task = new Task("Tarefa #" + i, baseDate.plusDays(i), false);
			task.setAppUser(appUser);
			taskRepository.save(task);
		}
	}
	
}
