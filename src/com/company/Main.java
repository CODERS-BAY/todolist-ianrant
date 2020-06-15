package com.company;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    static ToDoDAO toDoDAO = new ToDoDAO();
    static TaskDAO taskDAO = new TaskDAO();

    public static void addTodo(Scanner scanner) throws SQLException {

        System.out.println("\nPlease enter the name of the new Todo");
        String todoName = scanner.nextLine();
        toDoDAO.insertToDo(todoName);
        Integer todoId = toDoDAO.retrieveToDoIdFromDB(todoName);
        System.out.println("Do you want to add one or more Tasks to this ToDo?\ny/n?");
        String answer = scanner.nextLine();

        while (!answer.equals("n")) {
            System.out.println("\nPlease enter the name of the associated Task.");
            String taskName = scanner.nextLine();
            taskDAO.insertTask(taskName, todoId);
            System.out.println("\nDo you want to add another task?\ny/n?");
            answer = scanner.nextLine();
        }
    }

    public static void alterTodo(Scanner scanner) throws SQLException {

        System.out.println("\nPlease enter the name of the Todo that you want to edit");
        String todoName = scanner.nextLine();
        ToDo changeTodo = toDoDAO.selectSingleToDoWithTasks(todoName);
        changeTodo.printSingleTodoWithTasks();


        System.out.println("\nDo you want to change the name of the Todo?\ny/n?");
        String answer = "";
        String oldToDoName = "";
        String newToDoName = "";

        answer = scanner.nextLine();
        if (answer.equals("y")) {
            oldToDoName = todoName;
            System.out.println("\nPlease enter the new name of this Todo");
            newToDoName = scanner.nextLine();
            toDoDAO.updateToDo(oldToDoName, newToDoName);
            toDoDAO.selectSingleToDoWithTasks(newToDoName).printSingleTodoWithTasks();
        }

        Integer numberOfTasks = taskDAO.countAssociatedTasks(newToDoName); // keep track of associated Tasks

        System.out.println("\nDo you want to take any of the following actions for the associated Tasks:\nInsert/Update/Delete\ny/n?");
        answer = scanner.nextLine();
        if (answer.equals("y")) {

            while (answer.equals("y")) {

                System.out.println("\nDo you want to insert, update or delete a Task?\ni/u/d?");
                answer = scanner.nextLine();
                if (answer.equals("i")) {

                    System.out.println("\nPlease enter the name of the new Task.");
                    String taskName = scanner.nextLine();
                    Integer todoId = toDoDAO.retrieveToDoIdFromDB(newToDoName);
                    taskDAO.insertTask(taskName, todoId);

                } else if (answer.equals("u")) {
                    System.out.println("\nPlease enter the name of the Task what wou want to update.");
                    String oldTaskName = scanner.nextLine();
                    System.out.println("\nPlease enter the new name of the Task.");
                    String newTaskName = scanner.nextLine();
                    taskDAO.updateTask(oldTaskName, newTaskName);

                } else if (answer.equals("d")) {
                    System.out.println("\nPlease enter the name of the Task what wou want to delete.");
                    String deleteTaskName = scanner.nextLine();
                    System.out.println("\nPlease confirm! y/n!");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equals("y")) {
                        Integer todoId = toDoDAO.retrieveToDoIdFromDB(newToDoName);
                        taskDAO.deleteTask(deleteTaskName, todoId);
                    } else {
                        System.out.println("\nNo confirmation - deletion suspended!");
                    }
                }
                System.out.println("\nDo you want to take any further actions?\ny/n?");
                answer = scanner.nextLine();
            }
        }


    }

    public static String whatNext(Scanner scanner) {
        //printMenu();
        System.out.println("\nPlease enter another menu option by number");
        String param = scanner.nextLine();

        return param;
    }

    public static void printMenu() {
        System.out.println("\nHello User-ino, please choose one of the following options.\nType in the number of the action in the menu that you wish to take:");
        System.out.println("--------------");
        System.out.println("1. Show all ToDos without their associated tasks");
        System.out.println("2. Show a single ToDo with its associated tasks");
        System.out.println("3. Add a new Todo with its associated tasks (if any)");
        System.out.println("4. Change/update an existing Todo");
        System.out.println("5. Delete a single ToDo with its associated tasks");
        System.out.println("Q. Exit by typing 'q' or 'Q'\n");
    }

    public static void fillDatabase() throws SQLException {

        Integer todoId = 0;
        int todoCount = 0;
        int taskCount = 0;

        /////// NEW TODO
        toDoDAO.insertToDo("Cleaning");
        todoCount++;
        todoId = toDoDAO.retrieveToDoIdFromDB("Cleaning");
        //System.out.println(todoId);
        taskDAO.insertTask("Windows", todoId);
        taskCount++;
        taskDAO.insertTask("Floor", todoId);
        taskCount++;
        taskDAO.insertTask("Laundry", todoId);
        taskCount++;

        /////// NEW TODO
        toDoDAO.insertToDo("Studying");
        todoCount++;
        todoId = toDoDAO.retrieveToDoIdFromDB("Studying");
        //System.out.println(todoId);
        taskDAO.insertTask("Preview", todoId);
        taskCount++;
        taskDAO.insertTask("Question", todoId);
        taskCount++;
        taskDAO.insertTask("Read", todoId);
        taskCount++;
        taskDAO.insertTask("Reflect", todoId);
        taskCount++;
        taskDAO.insertTask("Recite", todoId);
        taskCount++;
        taskDAO.insertTask("Review", todoId);
        taskCount++;

        /////// NEW TODO
        toDoDAO.insertToDo("Groceries");
        todoCount++;
        todoId = toDoDAO.retrieveToDoIdFromDB("Groceries");
        //System.out.println(todoId);
        taskDAO.insertTask("Cucumbers", todoId);
        taskCount++;
        taskDAO.insertTask("apples", todoId);
        taskCount++;
        taskDAO.insertTask("letus", todoId);
        taskCount++;
        taskDAO.insertTask("musterD", todoId);
        taskCount++;
        taskDAO.insertTask("garlik", todoId);
        taskCount++;
        taskDAO.insertTask("brad", todoId);
        taskCount++;

        /////// NEW TODO
        toDoDAO.insertToDo("Childcare");
        todoCount++;
        todoId = toDoDAO.retrieveToDoIdFromDB("Childcare");
        //System.out.println(todoId);
        taskDAO.insertTask("diaper change", todoId);
        taskCount++;
        taskDAO.insertTask("carry", todoId);
        taskCount++;
        taskDAO.insertTask("love", todoId);
        taskCount++;


        /////// NEW TODO
        toDoDAO.insertToDo("Relationship");
        todoCount++;
        todoId = toDoDAO.retrieveToDoIdFromDB("Relationship");
        //System.out.println(todoId);
        taskDAO.insertTask("buy chocolates", todoId);
        taskCount++;
        taskDAO.insertTask("cook favorite dish", todoId);
        taskCount++;
        taskDAO.insertTask("clean up after oneself", todoId);
        taskCount++;
        taskDAO.insertTask("write nice card", todoId);
        taskCount++;

        String todoNotation = (todoCount > 1) ? "ToDos" : "ToDo";
        String taskNotation = (taskCount > 1) ? "Tasks" : "Task";
        System.out.println("\n" + todoCount + " " + todoNotation + " and " + taskCount + " associated " + taskNotation + " inserted");

        toDoDAO.closeDBConnection();
        taskDAO.closeDBConnection();
    }

    public static void main(String[] args) throws SQLException {
        // write your code here

        ToDo toDo = new ToDo();
        Task task = new Task();
        Scanner scanner = new Scanner(System.in);

        toDoDAO.initialReset();
        fillDatabase();
        printMenu();

        System.out.println("Enter your choice");
        String param = scanner.nextLine();
        //if (param == "")

        while (true) {

            switch (param) {
                case "1":
                    toDoDAO.selectAllToDoNoTasks();
                    param = whatNext(scanner);
                    break;
                case "2":
                    System.out.println("Please enter the todo by its exact name to show it");
                    String singleTodo = scanner.nextLine();
                    ToDo showSingleTodo = toDoDAO.selectSingleToDoWithTasks(singleTodo);
                    showSingleTodo.printSingleTodoWithTasks();
                    param = whatNext(scanner);
                    break;
                case "3":
                    addTodo(scanner);
                    param = whatNext(scanner);
                    break;
                case "4":
                    alterTodo(scanner);
                    param = whatNext(scanner);
                    break;
                case "5":
                    System.out.println("Please enter the todo by its exact name to delete it");
                    singleTodo = scanner.nextLine();
                    toDoDAO.deleteToDo(singleTodo);
                    param = whatNext(scanner);
                    break;
                case "Q":
                case "q":
                    System.out.println("You have selected 'Exit'. Good day to you! :)");
                    toDoDAO.closeDBConnection();
                    taskDAO.closeDBConnection();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose a valid option");
                    param = whatNext(scanner);
            }
        }
    }
}
