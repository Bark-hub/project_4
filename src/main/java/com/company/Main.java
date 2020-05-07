package com.company;

// Mubarik Abdi 5/6/2020

import java.util.*;
import com.google.gson.*;

public class Main extends Collection {

    public static Scanner input = new Scanner(System.in);
    //I used an ArrayList of objects which are instances of the Task class. Meaning each item in the list is a singular task.

    public static Collection collection = new Collection();


    public static void main(String[] args) {

        Gson gson = new Gson();

        String jsonData = "{\"List of Tasks\" : [ {\"title\" : \"Dog\", \"description\" : \"walk the dog\", \"priority\" : 4 }," +
                "{\"title\" : \"bee\", \"description\" : \"collect honey from bee colony\", \"priority\" : 5}," +
                "{\"title\" : \"School\", \"description\" : \"study for the exams\", \"priority\" : 4}," +
                "{\"title\" : \"car\", \"description\" : \"clean the car\", \"priority\" : 3} ] } ";

        JsonParser parser = new JsonParser();

        JsonObject listObject = parser.parse(jsonData).getAsJsonObject();

        for (Map.Entry<String, JsonElement> listMap: listObject.entrySet()){
            if(listMap.getValue().isJsonArray()){
                System.out.println(listMap.getKey());

                JsonArray listArray = listMap.getValue().getAsJsonArray();

                for (JsonElement task : listArray){
                    Task job = gson.fromJson(task, Task.class); //LOADS
                    System.out.println(job);
                    collection.addingTask(job);
                }

            }
        }

        Task testing = new Task("test", "testing", 1);
        collection.addingTask(testing);

        //The menu is below. Just a simple interface for the user.
        System.out.println("---MENU-------MENU-------MENU----");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) List Specific tasks.");
        System.out.println("(0) Exit.");
        System.out.println("Please Choose One: ");

        //It all begins with the userInput() method because it controls the looping and selection of other methods.
        //I used a switch case that is contained within a while loop. The switch selects the methods.
        String loop = userInput();

        while (!loop.equals("0")) {
            switch (loop) {
                case "1":
                    add();
                    System.out.println("what else do you wanna do? (1)add, (2)remove, (3)update, (4)list All, (5)list Specific, (0)exit");
                    loop = userInput();
                    break;
                case "2":
                    remove();
                    System.out.println("what else do you wanna do? (1)add, (2)remove, (3)update, (4)list All, (5)list Specific, (0)exit");
                    loop = userInput();
                    break;
                case "3":
                    update();
                    System.out.println("what else do you wanna do? (1)add, (2)remove, (3)update, (4)list All, (5)list Specific, (0)exit");
                    loop = userInput();
                    break;
                case "4":
                    testing.sortList();
                    System.out.println("what else do you wanna do? (1)add, (2)remove, (3)update, (4)list All, (5)list Specific, (0)exit");
                    loop = userInput();
                    break;
                case "5":
                    listSpecifically();
                    System.out.println("what else do you wanna do? (1)add, (2)remove, (3)update, (4)list All, (5)list Specific, (0)exit");
                    loop = userInput();
                    break;
                case "0":
                    break;
            }
        }
        System.out.println("\n" + gson.toJson(collection.ToDo)); //SAVES
    }

    //The input method is below and this particular method has exception handling.
    //This method is the only one with a returning value.
    public static String userInput(){
        String userChoice = input.nextLine();
        Boolean pass = false;
        while(!pass) {
            try {
                int userIntChoice = Integer.parseInt(userChoice);
                if (userIntChoice <= 5 && userIntChoice >= 0) {
                    pass = true;
                }else{
                    System.out.println(userChoice + " IS NOT A GOOD NUMBER, PLEASE ENTER A NUMBER OR ELSE. >:<");
                    userChoice = input.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println(userChoice + " IS NOT A GOOD NUMBER, PLEASE ENTER A NUMBER OR ELSE. >:<");
                userChoice = input.nextLine();
            }
        }
        return userChoice;
    }

    //Below is the method for adding a task. It simply creates an instance of the class Task while asking the user for input.
    //As you can see, after the exception handling in the method before, I did not do exception handling for the rest.
    //I believe that if the user got to this point, then their competent enough to not make mistakes.
    public static void add(){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Adding Task: ");

        System.out.println("Input title: ");
        String title = input.nextLine();
        System.out.println("Input Description: ");
        String description = input.nextLine();
        System.out.println("Input Priority: (from 0 to 5 being most important)");
        int priority = input.nextInt();

        Task item = new Task(title, description, priority);
        collection.addingTask(item);
        System.out.println("------Successfully Added-------");
    }

    //The listing method which shows the specific priority level the user is looking for.
    public static void listSpecifically(){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Listing Task(s): ");
        System.out.println("What priority of tasks are you looking for?");
        int priorityIndex = input.nextInt();

        // I learned something new! or maybe relearned the sort method.
        Collections.sort(collection.ToDo, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getTitle().compareTo(t2.getTitle());
            }
        });

        for (Task priority : collection.ToDo) {
            if (priority.getPriority() == priorityIndex) {
                System.out.println(priority);
            }
        }

        System.out.println("------Successfully Listed-------");
    }

    //The method below is the remove method. The user needs to specify which task to remove by number.
    public static void remove(){
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Removing Task: ");
        System.out.println("Which would you like to Remove? (enter a number as in 1 for first Task or 2 as in second task)");
        int taskNumber = input.nextInt();
        int taskIndex = taskNumber - 1;
        collection.ToDo.remove(taskIndex);
        System.out.println("------Successfully Removed-------");
    }

    //The method below is a combination of the remove and add method but with a twist.
    //It allows the user to specify which task to edit / update.
    public static void update(){
        System.out.println("Updating Task: ");
        System.out.println("Which would you like to Update? (enter a number as in 1 for first Task or 2 as in second task)");
        int taskNumber = input.nextInt();
        int taskIndex = taskNumber - 1;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter New Title: ");
        String newTitle = input.nextLine();
        System.out.println("Enter New Description: ");
        String newDescription = input.nextLine();
        System.out.println("Enter New Priority: ");
        int newPriority = input.nextInt();

        collection.ToDo.get(taskIndex).setTitle(newTitle);
        collection.ToDo.get(taskIndex).setDescription(newDescription);
        collection.ToDo.get(taskIndex).setPriority(newPriority);
        System.out.println("------Successfully Updated-------");

    }

}
