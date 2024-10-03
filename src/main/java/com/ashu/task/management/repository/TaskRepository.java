package com.ashu.task.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.task.management.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
    
}

//JpaRepository<Task, Long>: This is a Spring Data JPA interface that provides CRUD (Create, Read, Update, Delete) operations and other database-related methods for the Task entity. The Task represents the type of entity, and Long is the type of the primary key (ID) of that entity.