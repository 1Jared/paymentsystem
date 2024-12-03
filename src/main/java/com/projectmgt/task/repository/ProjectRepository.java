package com.projectmgt.task.repository;

import com.projectmgt.task.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}


