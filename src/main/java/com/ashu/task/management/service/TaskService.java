package com.ashu.task.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashu.task.management.model.Task;
import com.ashu.task.management.repository.TaskRepository;

@Service  //indicates that this class is a service component in the spring context

public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    //Returns a list of all tasks.
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    //Retrieves a task by its ID.
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }
    //Saves a new task to the database.
    public void saveTask(Task task){
        taskRepository.save(task);
    }

    //Deletes a task by its ID.
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }



}
