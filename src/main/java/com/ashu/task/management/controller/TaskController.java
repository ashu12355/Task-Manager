package com.ashu.task.management.controller;

import java.util.Optional;
import java.util.logging.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashu.task.management.model.Task;
import com.ashu.task.management.service.TaskService;

@Controller
@RequestMapping({"/tasks","/"})
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String listTasks(Model model){
        model.addAttribute("tasks",taskService.getAllTask());
        return "task_list";
    }

    @GetMapping("/create")
    public String createTask(Model model){
        model.addAttribute("task",new Task());
        return "task_form";
    }

    @PostMapping("/create")
    public String saveTask(@ModelAttribute("task") Task task){
        if(task.getId() != null){
            taskService.saveTask(task);
        }
        else {
            taskService.saveTask(task);
        }
      
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id , Model model){
        Optional<Task> task = taskService.getTaskById(id);
        task.ifPresent(value -> model.addAttribute("task", value));
        return "task_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    
}
