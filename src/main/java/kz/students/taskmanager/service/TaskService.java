package kz.students.taskmanager.service;

import kz.students.taskmanager.dto.TaskRequest;
import kz.students.taskmanager.dto.TaskResponse;
import kz.students.taskmanager.entity.Project;
import kz.students.taskmanager.entity.Task;
import kz.students.taskmanager.entity.TaskStatus;
import kz.students.taskmanager.entity.User;
import kz.students.taskmanager.repository.ProjectRepository;
import kz.students.taskmanager.repository.TaskRepository;
import kz.students.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskResponse create(TaskRequest request) {
        Task task = new Task();
        fill(task, request);
        return TaskResponse.from(taskRepository.save(task));
    }

    public List<TaskResponse> getAll() {
        return taskRepository.findAll().stream().map(TaskResponse::from).toList();
    }

    public TaskResponse getById(Long id) {
        return TaskResponse.from(findOrThrow(id));
    }

    public TaskResponse update(Long id, TaskRequest request) {
        Task task = findOrThrow(id);
        fill(task, request);
        return TaskResponse.from(taskRepository.save(task));
    }

    public void delete(Long id) {
        taskRepository.delete(findOrThrow(id));
    }

    private void fill(Task task, TaskRequest request) {
        Project project = projectRepository.findById(request.projectId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status() != null ? request.status() : TaskStatus.TODO);
        task.setProject(project);

        if (request.assigneeId() != null) {
            User assignee = userRepository.findById(request.assigneeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            task.setAssignee(assignee);
        } else {
            task.setAssignee(null);
        }
    }

    private Task findOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }
}