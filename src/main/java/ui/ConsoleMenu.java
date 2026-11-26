
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

    private void displayMenu() {

        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. Show All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Remove Book");
        System.out.println("7. Edit Book");
        System.out.println("0. Exit");

    }

    public void start() {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

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
                    borrowBook();
                    break;

                case 5:
                    returnBook();
                    break;

                case 6:
                    removeBook();
                    break;

                case 7:
                    editBook();
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

    private int readInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private int readYear(String message) {

        while (true) {

            int year = readInt(message);

            if (year > 0 && year <= java.time.Year.now().getValue()) {
                return year;
            }

            System.out.println("Invalid year!");
        }
    }

    private String readNonEmpty(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty!");
        }
    }

    private void addBook() {

        System.out.println("\n--- Add Book ---");

        String title = readNonEmpty("Enter book title: ");
        String author = readNonEmpty("Enter author name: ");
        String isbn = readNonEmpty("Enter ISBN: ");

        if (library.isIsbnExists(isbn)){
            System.out.println("A book with this ISBN already exists!");
            return;
        }

        int year = readYear("Enter publication year: ");

        Book book = new Book(title, author, isbn, year);

        library.addBook(book);

        System.out.println("Book added successfully!");
    }

    private void showAllBooks() {
        displayBooks(library.getAllBooks());
    }

    private void searchBooks() {

        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Year");
        System.out.println("4. Search by Availability");

        int choice = readInt("Choose search type: ");

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
                int year = readInt("Enter year: ");
                displayBooks(library.searchByYear(year));
                break;

            case 4:
                System.out.print("Available books? (true/false): ");
                String availabilityInput = scanner.nextLine();

                boolean available;

                if (availabilityInput.equalsIgnoreCase("true")) {
                    available = true;
                } else if (availabilityInput.equalsIgnoreCase("false")) {
                    available = false;
                } else {
                    System.out.println("Please enter true or false.");
                    return;
                }

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

    private void borrowBook() {

        System.out.println("\n--- Borrow Book ---");

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter borrower name: ");
        String borrowerName = scanner.nextLine();

        boolean success = library.borrowBook(isbn, borrowerName);

        if (success) {
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Borrowing failed!");
            System.out.println("Book not found or already borrowed.");
        }
    }

    private void returnBook() {

        System.out.println("\n--- Return Book ---");

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        boolean success = library.returnBook(isbn);

        if (success) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Returning failed!");
            System.out.println("Book not found or is not currently borrowed.");
        }
    }

    private void removeBook() {

        System.out.println("\n--- Remove Book ---");

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        boolean success = library.removeBook(isbn);

        if (success) {
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Removal failed!");
            System.out.println("Book not found or currently borrowed.");
        }
    }

    private void editBook() {

        System.out.println("\n--- Edit Book ---");

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("\nWhat do you want to edit?");
        System.out.println("1. Edit Title");
        System.out.println("2. Edit Author");
        System.out.println("3. Edit Publication Year");
        System.out.println("0. Back");

        int choice = readInt("Choose an option: ");

        boolean success = false;

        switch (choice) {

            case 1:
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                success = library.editBookTitle(isbn, newTitle);
                break;

            case 2:
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                success = library.editBookAuthor(isbn, newAuthor);
                break;

            case 3:
                int newYear = readYear("Enter new publication year: ");
                success = library.editBookYear(isbn, newYear);
                break;

            case 0:
                return;

            default:
                System.out.println("Invalid option!");
                return;
        }

        if (success) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }
}
