package br.com.bluetask.app.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	
	@NotEmpty(message = "A descrição da tarefa é obrigatória")
	@Length(min = 3, max = 40, message = "O tamanho da tarefa é inválido")
	private String description;
	
	@NotNull(message = "A data da tarefa é obrigatória")
	@FutureOrPresent(message = "A data não pode estar no passado")
	private LocalDate whenToDo;
	
	private Boolean done = false;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
	//@NotNull(message = "O usuário da tarefa é obrigatório")
	private AppUser appUser;

	public Task(String description, LocalDate whenToDo, Boolean done) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}

}
