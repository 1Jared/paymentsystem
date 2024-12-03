package com.projectmgt.task.controller;

import com.projectmgt.task.dto.ProjectSummaryDTO;
import com.projectmgt.task.entity.Project;
import com.projectmgt.task.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Api(value = "Project Management")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @ApiOperation(value = "Creating a new project")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.ok(savedProject);
    }

    @GetMapping
    @ApiOperation(value = "Fetch all projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    @ApiOperation(value = "Get a project by its ID")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Optional<Project> project = projectService.getProjectById(projectId);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{projectId}")
    @ApiOperation(value = "Delete project")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    @ApiOperation(value = "Get a summary of all projects with counts for a task by status")
    public ResponseEntity<List<ProjectSummaryDTO>> getProjectSummary() {
        List<ProjectSummaryDTO> summary = projectService.getProjectSummary();
        return ResponseEntity.ok(summary);
    }

}


