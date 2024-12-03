package com.projectmgt.task.repository;

import com.projectmgt.task.entity.Status;
import com.projectmgt.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId")
    Page<Task> findByProjectId(Long projectId, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId AND t.status = :status")
    Page<Task> findByProjectIdAndStatus(Long projectId, Status status, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId AND t.dueDate = :dueDate")
    Page<Task> findByProjectIdAndDueDate(Long projectId, Date dueDate, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId AND t.status = :status AND t.dueDate = :dueDate")
    Page<Task> findByProjectIdAndStatusAndDueDate(Long projectId, Status status, Date dueDate, Pageable pageable);

    @Query("SELECT t.project.id AS projectId, t.status AS status, COUNT(t) AS count " +
            "FROM Task t GROUP BY t.project.id, t.status")
    List<Map<String, Object>> countTasksByProjectAndStatus();
}