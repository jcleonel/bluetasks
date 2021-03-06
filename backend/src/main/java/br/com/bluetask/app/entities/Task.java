package br.com.bluetask.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bluetask.app.services.utils.TaskListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(TaskListener.class)
@NoArgsConstructor
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "A descri??o da tarefa ? obrigat?ria")
	@Length(min = 3, max = 80, message = "O tamanho da tarefa ? inv?lido")
	private String description;
	
	@NotNull(message = "A data da tarefa ? obrigat?ria")
	@FutureOrPresent(message = "A data n?o pode estar no passado")
	private LocalDate whenToDo;
	
	private Boolean done = false;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	//@NotNull(message = "O usu?rio da tarefa ? obrigat?rio")
	@JsonIgnore
	private AppUser appUser;

	public Task(String description, LocalDate whenToDo, Boolean done) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}

}
