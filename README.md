# Task Manager

Backend веб-приложения для управления проектами и задачами.
Курсовой проект по предмету «Java Programming».

## Технологии
- Java 17, Spring Boot
- Spring Data JPA + Hibernate
- PostgreSQL
- Spring Security + JWT
- Swagger (springdoc-openapi)
- Maven, Lombok

## Архитектура
Слои: Controller → Service → Repository → Entity.
Данные передаются через DTO (Record-классы).
Ошибки обрабатываются централизованно (GlobalExceptionHandler).

## Сущности
- User (роли USER и ADMIN)
- Project (принадлежит пользователю)
- Task (принадлежит проекту, может быть назначена пользователю)

## Запуск
1. Установить Java 17+ и PostgreSQL.
2. Создать базу данных `taskmanager`.
3. В `src/main/resources/application.properties` указать пароль от PostgreSQL.
4. Запустить `TaskmanagerApplication`.
5. Приложение: http://localhost:8080
6. Документация API (Swagger): http://localhost:8080/swagger-ui.html

## Доступ
| Метод | Адрес | Доступ |
|-------|-------|--------|
| POST | /api/auth/register, /api/auth/login | все |
| GET, POST, PUT | /api/projects, /api/tasks | авторизованные |
| DELETE | /api/** | ADMIN |
| любой | /api/users/** | ADMIN |

Защищённые запросы требуют заголовок `Authorization: Bearer <token>`.
Тестовый администратор создаётся автоматически: `admin` / `admin123`.