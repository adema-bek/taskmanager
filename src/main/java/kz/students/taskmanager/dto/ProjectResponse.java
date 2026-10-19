package kz.students.taskmanager.dto;

import kz.students.taskmanager.entity.Project;

public record ProjectResponse(Long id, String name, String description, Long ownerId) {

    public static ProjectResponse from(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getOwner().getId()
        );
    }
}