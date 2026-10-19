package kz.students.taskmanager.service;

import kz.students.taskmanager.dto.ProjectRequest;
import kz.students.taskmanager.dto.ProjectResponse;
import kz.students.taskmanager.entity.Project;
import kz.students.taskmanager.entity.User;
import kz.students.taskmanager.repository.ProjectRepository;
import kz.students.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponse create(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.name());
        project.setDescription(request.description());
        project.setOwner(findUserOrThrow(request.ownerId()));
        return ProjectResponse.from(projectRepository.save(project));
    }

    public List<ProjectResponse> getAll() {
        return projectRepository.findAll().stream().map(ProjectResponse::from).toList();
    }

    public ProjectResponse getById(Long id) {
        return ProjectResponse.from(findOrThrow(id));
    }

    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = findOrThrow(id);
        project.setName(request.name());
        project.setDescription(request.description());
        project.setOwner(findUserOrThrow(request.ownerId()));
        return ProjectResponse.from(projectRepository.save(project));
    }

    public void delete(Long id) {
        projectRepository.delete(findOrThrow(id));
    }

    private Project findOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}