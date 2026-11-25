package ui;

import model.Library;

import java.util.Scanner;

public class ConsoleMenu {

    private final Library library;
    private final Scanner scanner;

    public ConsoleMenu(){
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    private void displayMenu() {

        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. Show All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Remove Book");
        System.out.println("0. Exit");

        System.out.print("Enter your choice: ");
    }

    public void start() {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Add Book");
                    break;

                case 2:
                    System.out.println("Show All Books");
                    break;

                case 3:
                    System.out.println("Search Book");
                    break;

                case 4:
                    System.out.println("Borrow Book");
                    break;

                case 5:
                    System.out.println("Return Book");
                    break;

                case 6:
                    System.out.println("Remove Book");
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

}
