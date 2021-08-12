package br.com.bluetask.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bluetask.app.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal} AND t.description = ?1")
	Task findByDescription(String description);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal} ORDER BY t.whenToDo ASC")
	List<Task> findAll();

//	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
//	Page<Task> findAllTasksByUser(Pageable pageable);

//	
//	@Override
//	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
//	Page<Task> findAll(Pageable pageable);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal} AND t.id = ?1")
	Optional<Task> findById(Integer id);

}
