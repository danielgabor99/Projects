package springjpa.bookstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpa.bookstore.core.domain.Book;
import springjpa.bookstore.core.domain.Client;
import springjpa.bookstore.core.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {

        log.trace("getAllBooks --- method entered");

        List<Book> result = bookRepository.findAll();

        log.trace("getAllBooks: result={}", result);

        return result;
    }

    @Override
    public Book saveBook(Book book) {
        log.trace("saveBook --- method entered");
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Long id, Book book) {
        log.trace("updateBook --- method entered");
        Book update = bookRepository.findById(id).orElse(book);
        update.setAuthor(book.getAuthor());
        update.setBookName(book.getBookName());
        update.setBookPrice(book.getBookPrice());

        log.trace("updateBook --- bookupdated{}", update);
        return update;

    }

    @Override
    public Book getBookById(Long id) {
        log.trace("getBookById --- method entered");
        Book update = bookRepository.findById(id).orElse(new Book("", "", 0));
        log.trace("updateBook --- bookById{}", update);
        return update;
    }


    @Override
    public void deleteBook(Long id) {
        log.trace("getBookById --- method entered");
        bookRepository.deleteById(id);
    }
}
