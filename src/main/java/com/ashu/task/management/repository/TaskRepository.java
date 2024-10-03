package com.ashu.task.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashu.task.management.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
    
}
