package kz.students.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.students.taskmanager.entity.TaskStatus;

public record TaskRequest(
        @NotBlank String title,
        String description,
        TaskStatus status,
        @NotNull Long projectId,
        Long assigneeId
) {
}