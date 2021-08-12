package br.com.bluetask.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluetask.app.dto.TaskDTO;
import br.com.bluetask.app.entities.Task;
import br.com.bluetask.app.repository.TaskRepository;
import br.com.bluetask.app.services.exceptions.DuplicateTaskException;
import br.com.bluetask.app.services.exceptions.ResourceNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Transactional(readOnly = true)
	public List<TaskDTO> findAll() {
		List<Task> tasksList = taskRepository.findAll();
		return tasksList.stream().map(x -> new TaskDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public TaskDTO findById(Integer id) {
		Optional<Task> obj = taskRepository.findById(id);
		Task entity = obj.orElseThrow(() -> new ResourceNotFoundException("Task não encontrada!"));
		return new TaskDTO(entity);
	}

	@Transactional
	public TaskDTO update(Integer id, TaskDTO dto) {

		try {
			Task entity = taskRepository.getOne(id);
			entity.setDescription(dto.getDescription());
			entity.setWhenToDo(dto.getWhenToDo());
			entity.setDone(dto.getDone());
			entity = taskRepository.save(entity);

			return new TaskDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado!");
		}
	}

	@Transactional
	public TaskDTO updateChangeds(Integer id, TaskDTO dto) {

		try {
			Task entity = taskRepository.getOne(id);
			entity.setDescription(dto.getDescription() != null ? dto.getDescription() : entity.getDescription());
			entity.setWhenToDo(dto.getWhenToDo() != null ? dto.getWhenToDo() : entity.getWhenToDo());
			entity.setDone(dto.getDone() != null ? dto.getDone() : entity.getDone());
			entity = taskRepository.save(entity);

			return new TaskDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado!");
		}
	}

	@Transactional
	public TaskDTO insert(TaskDTO taskDto) {
		
		Task taskDB = taskRepository.findByDescription(taskDto.getDescription());
		boolean duplicate = false;		
		
		if (taskDB != null) {
			if ((taskDto.getId() == null || taskDto.getId() == 0) && taskDto.getDescription().equals(taskDB.getDescription())) {
				duplicate = true;
			}
			
			if (taskDto.getId() != null && taskDto.getId() > 0 && !taskDto.getId().equals(taskDB.getId())) {
				duplicate = true;
			}
		}
		
		if (duplicate) {
			throw new DuplicateTaskException("Já existe uma tarefa com essa descrição");
		}
				
		Task entity = new Task();
		entity.setDescription(taskDto.getDescription());
		entity.setWhenToDo(taskDto.getWhenToDo());
		entity.setDone(taskDto.getDone());
		
		entity = taskRepository.save(entity);

		return new TaskDTO(entity);
	}

}
