package br.com.bluetask.app.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bluetask.app.domain.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	private LocalDate whenToDo;
	private Boolean done = false;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;

	public Task(String description, LocalDate whenToDo, Boolean done) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}

}
