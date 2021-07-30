package br.com.bluetask.app.domain.task;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	Task findByDescription(String description);

	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
	Page<Task> findAll(Pageable pageable);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal} AND t.id = ?1")
	Optional<Task> findById(Integer id);

}
