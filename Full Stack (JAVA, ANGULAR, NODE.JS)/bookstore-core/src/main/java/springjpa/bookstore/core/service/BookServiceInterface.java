package springjpa.bookstore.core.service;

import springjpa.bookstore.core.domain.Book;

import java.util.List;

/**
 * Created by radu.
 */
public interface BookServiceInterface {
    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    Book getBookById(Long id);

    void deleteBook(Long id);
}