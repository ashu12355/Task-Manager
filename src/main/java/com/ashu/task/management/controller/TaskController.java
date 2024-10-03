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

@Controller //This annotation marks the class as a Spring MVC controller, indicating that it is responsible for handling HTTP requests and returning views (web pages).


//@RequestMapping("/tasks"):
// Purpose: This annotation is used to define a base URL for all request mappings in this controller. In this case, all mappings in the TaskController will start with / or tasks.

@RequestMapping({"/tasks","/"})
public class TaskController {

   //@Autowired = Purpose: This annotation is used to automatically inject dependencies into the class. Here, it injects an instance of the TaskService class, allowing the controller to access the service layer.
    // Usage: It eliminates the need to create the TaskService instance manually, promoting loose coupling.
    @Autowired 
    private TaskService taskService;


    //listTasks(Model model): Handles GET requests to display all tasks. It adds the list of tasks to the model and returns the view name task_list.
    //@GetMapping : This annotation is a specialized version of @RequestMapping used specifically for handling HTTP GET requests. It allows the method to respond to GET requests for the specified URL.

    @GetMapping 
    public String listTasks(Model model){
        model.addAttribute("tasks",taskService.getAllTask());
        return "task_list";
    }

    //createTask(Model model): Displays the form for creating a new task.

    @GetMapping("/create")
    public String createTask(Model model){
        model.addAttribute("task",new Task());
        return "task_form";
    }

    //saveTask(@ModelAttribute("task") Task task): Handles the form submission to save a new task.
    //@ModelAttribute("task") Task task:
    // This annotation binds the form data submitted by the user to a Task object.
    // The string "task" refers to the name of the model attribute (typically the name of the form in the HTML) that the form data will be bound to.
    // When the form is submitted, Spring automatically maps the form fields to the properties of the Task object.

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

    // editTaskForm(@PathVariable Long id, Model model): Displays the form for editing an existing task.

    //@PathVariable:
    // Purpose: This annotation is used to extract values from the URI (URL path). It binds a method parameter to a URI template variable.
    // Usage: In methods like editTaskForm and deleteTask, it retrieves the task ID from the URL.

    //Optional<Task> task = taskService.getTaskById(id);
    // Purpose: This line calls the getTaskById method of the taskService to retrieve the task with the specified ID.
    // Optional<Task>:
    // The return type Optional<Task> is a container that may or may not contain a non-null value.
    // It helps to handle the case where the task might not be found, avoiding potential NullPointerExceptions.

    //task.ifPresent(value -> model.addAttribute("task", value));
    // Purpose: This line checks if the Optional<Task> contains a value.
    // Logic:
    // If a task with the specified ID is found (i.e., the Optional is not empty), the ifPresent method executes the provided lambda expression.
    // The lambda expression takes the found task (value) and adds it to the model with the key "task".
    // This makes the task data available to the view, allowing the form to be populated with the existing task's details for editing.

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id , Model model){
        Optional<Task> task = taskService.getTaskById(id);
        task.ifPresent(value -> model.addAttribute("task", value));
        return "task_form";
    }

    // deleteTask(@PathVariable Long id): Handles the request to delete a task.

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    
}

//@ModelAttribute:
// Purpose: This annotation is used to bind the form data (from the HTML form) to a model object. It allows Spring to automatically populate the object from the form submission.
// Usage: In the saveTask method, it binds the submitted form data to a Task object.