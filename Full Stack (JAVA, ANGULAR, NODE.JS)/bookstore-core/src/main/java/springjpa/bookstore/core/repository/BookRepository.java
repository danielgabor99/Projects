package springjpa.bookstore.core.repository;

import springjpa.bookstore.core.domain.Book;
import springjpa.bookstore.core.repository.BookstoreRepository;

/**
 * Created by radu.
 */
public interface BookRepository extends BookstoreRepository<Book, Long> {
}
