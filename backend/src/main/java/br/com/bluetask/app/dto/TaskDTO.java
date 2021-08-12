package br.com.bluetask.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.bluetask.app.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String description;
	private LocalDate whenToDo;
	private Boolean done;

	public TaskDTO(Task entity) {
		this.id = entity.getId();
		this.description = entity.getDescription();
		this.whenToDo = entity.getWhenToDo();
		this.done = entity.getDone();
	}

}
