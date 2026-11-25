package ui;

import model.Book;
import service.Library;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final Library library;
    private final Scanner scanner;

    public ConsoleMenu() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    // Display the main menu
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

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    showAllBooks();
                    break;

                case 3:
                    searchBooks();
                    break;

                case 4:
                    System.out.println("Borrow Book - Coming Soon");
                    break;

                case 5:
                    System.out.println("Return Book - Coming Soon");
                    break;

                case 6:
                    System.out.println("Remove Book - Coming Soon");
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

    private void addBook() {

        System.out.println("\n--- Add Book ---");

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter publication year: ");
        int year = Integer.parseInt(scanner.nextLine());

        Book book = new Book(title, author, isbn, year);

        library.addBook(book);

        System.out.println("Book added successfully!");
    }

    private void showAllBooks() {

        displayBooks(library.getAllBooks());
    }

    // Search books
    private void searchBooks() {

        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Year");
        System.out.println("4. Search by Availability");

        System.out.print("Choose search type: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {

            case 1:
                System.out.print("Enter title: ");
                displayBooks(library.searchByTitle(scanner.nextLine()));
                break;

            case 2:
                System.out.print("Enter author: ");
                displayBooks(library.searchByAuthor(scanner.nextLine()));
                break;

            case 3:
                System.out.print("Enter year: ");
                int year = Integer.parseInt(scanner.nextLine());
                displayBooks(library.searchByYear(year));
                break;

            case 4:
                System.out.print("Available books? (true/false): ");
                boolean available = Boolean.parseBoolean(scanner.nextLine());
                displayBooks(library.searchByAvailability(available));
                break;

            default:
                System.out.println("Invalid search option!");
        }
    }

    private void displayBooks(List<Book> books) {

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {

            System.out.println("--------------------");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Year: " + book.getPublicationYear());
            System.out.println("Available: " + book.isAvailable());
        }

        System.out.println("--------------------");
    }
}
