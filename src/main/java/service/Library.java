package service;

import model.Book;
import model.Loan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books);
    }

    public List<Book> searchByTitle(String title){
        List<Book> results = new ArrayList<>();

        for (Book book : this.books){
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                results.add(book);
            }
        }

        return results;
    }

    public List<Book> searchByAuthor(String author){
        List<Book> results = new ArrayList<>();

        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    public List<Book> searchByYear (int year){
        List<Book> results = new ArrayList<>();

        for (Book book : this.books){
            if (book.getPublicationYear() == year){
                results.add(book);
            }
        }

        return results;
    }

    public List<Book> searchByAvailability(boolean available){
        List<Book> results = new ArrayList<>();

        for (Book book : this.books){
            if (book.isAvailable() == available){
                results.add(book);
            }
        }

        return  results;
    }

    public boolean removeBook(String isbn){
        Iterator<Book> bookIterator = this.books.iterator();

        while (bookIterator.hasNext()){
            Book book = bookIterator.next();

            if (book.getIsbn().equals(isbn)){

                if (!book.isAvailable()){
                    return false;
                }

                bookIterator.remove();
                return true;
            }
        }

        return false;
    }

    public boolean borrowBook (String isbn, String borrowerName){

        for (Book book : this.books){
            if(book.getIsbn().equals(isbn)){
                if(!book.isAvailable()){
                    return false;
                }
                Loan loan = new Loan(book, borrowerName);
                book.setAvailable(false);
                this.loans.add(loan);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String isbn){
        for (Loan loan : this.loans){
            if (loan.getBook().getIsbn().equals(isbn) && loan.getReturnDate() == null){
                loan.setReturnDate(LocalDate.now());
                loan.getBook().setAvailable(true);

                return true;
            }
        }
        return false;
    }

    public boolean editBookTitle(String isbn, String newTitle) {

        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(newTitle);
                return true;
            }
        }

        return false;
    }

    public boolean editBookAuthor(String isbn, String newAuthor) {

        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                book.setAuthor(newAuthor);
                return true;
            }
        }

        return false;
    }

    public boolean editBookYear(String isbn, int newYear) {

        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                book.setPublicationYear(newYear);
                return true;
            }
        }

        return false;
    }
}
