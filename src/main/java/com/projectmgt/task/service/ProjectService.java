package com.projectmgt.task.service;

import com.projectmgt.task.dto.ProjectSummaryDTO;
import com.projectmgt.task.entity.Project;
import com.projectmgt.task.entity.Status;
import com.projectmgt.task.repository.ProjectRepository;
import com.projectmgt.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public List<ProjectSummaryDTO> getProjectSummary() {
        List<Project> projects = projectRepository.findAll();
        List<Map<String, Object>> taskCounts = taskRepository.countTasksByProjectAndStatus();

        Map<Long, Map<Status, Long>> projectTaskCounts = new HashMap<>();
        for (Map<String, Object> result : taskCounts) {
            Long projectId = (Long) result.get("projectId");
            Status status = (Status) result.get("status");
            Long count = (Long) result.get("count");

            projectTaskCounts
                    .computeIfAbsent(projectId, k -> new HashMap<>())
                    .put(status, count);
        }

        return projects.stream().map(project -> {
            ProjectSummaryDTO summaryDTO = new ProjectSummaryDTO();
            summaryDTO.setProjectId(project.getId());
            summaryDTO.setProjectName(project.getName());
            summaryDTO.setTaskCounts(projectTaskCounts.getOrDefault(project.getId(), new HashMap<>()));
            return summaryDTO;
        }).collect(Collectors.toList());
    }
}

