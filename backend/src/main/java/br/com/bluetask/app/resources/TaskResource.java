package br.com.bluetask.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bluetask.app.dto.TaskDTO;
import br.com.bluetask.app.services.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<List<TaskDTO>> findAll() {
		List<TaskDTO> list = taskService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Integer id) {
		TaskDTO taskDTO = taskService.findById(id);		
		return ResponseEntity.ok().body(taskDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
		taskDTO = taskService.update(id, taskDTO);		
		return ResponseEntity.ok().body(taskDTO);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> updateChangeds(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
		taskDTO = taskService.updateChangeds(id, taskDTO);		
		return ResponseEntity.ok().body(taskDTO);
	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> insert(@RequestBody TaskDTO taskDTO) {
		taskDTO = taskService.insert(taskDTO);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(taskDTO.getId()).toUri();		
		return ResponseEntity.created(uri).body(taskDTO);
	}

}
