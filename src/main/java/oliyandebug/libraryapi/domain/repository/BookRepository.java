package oliyandebug.libraryapi.domain.repository;

import oliyandebug.libraryapi.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsBookByIsbn(String isbn);
    List<Book> findAllByBorrowedTrue();
    List<Book> findAllByBorrowedFalse();
    boolean existsBookByIdAndBorrowedTrue(long id);
    boolean existsBookByIdAndBorrowedFalse(long id);

}
