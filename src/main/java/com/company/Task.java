package com.company;

import java.util.*;


// Mubarik Abdi 3/1/2020

public class Task extends com.company.Main implements Sort{

    //The properties of an instance of the class Task
    private String title;
    private String description;
    private int priority;

    //I made two constructors, the first is for an empty task and the second is for a filled task.
    public Task() {
    }

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    // getters and setters and getters and setters...
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void sortList() {

        Collections.sort(collection.ToDo, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getTitle().compareTo(t2.getTitle());
            }
        });

        System.out.println("");
        for (Task priority : collection.ToDo) {
            if (priority.getPriority() == 1) {
                System.out.println(priority);
            }
        }
        for (Task priority : collection.ToDo) {
            if (priority.getPriority() == 2) {
                System.out.println(priority);
            }
        }
        for (Task priority : collection.ToDo) {
            if (priority.getPriority() == 3) {
                System.out.println(priority);
            }
        }
        for (Task priority : collection.ToDo) {
            if (priority.getPriority() == 4) {
                System.out.println(priority);
            }
        }
        for (Task priority : collection.ToDo) {
            if (priority.getPriority() == 5) {
                System.out.println(priority);
            }
        }
    }


    //Below is my toString.
    //Note: it looks different when executed.
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}

