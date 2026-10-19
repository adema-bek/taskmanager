package kz.students.taskmanager.dto;

import kz.students.taskmanager.entity.Task;
import kz.students.taskmanager.entity.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Long projectId,
        Long assigneeId
) {

    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getProject().getId(),
                task.getAssignee() != null ? task.getAssignee().getId() : null
        );
    }
}