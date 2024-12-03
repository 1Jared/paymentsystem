package com.projectmgt.task.dto;

import com.projectmgt.task.entity.Status;
import java.util.Map;

public class ProjectSummaryDTO {
    private Long projectId;
    private String projectName;
    private Map<Status, Long> taskCounts;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Map<Status, Long> getTaskCounts() {
        return taskCounts;
    }

    public void setTaskCounts(Map<Status, Long> taskCounts) {
        this.taskCounts = taskCounts;
    }
}
