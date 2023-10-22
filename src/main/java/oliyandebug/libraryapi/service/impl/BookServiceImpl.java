package oliyandebug.libraryapi.service.impl;

import oliyandebug.libraryapi.domain.model.Book;
import oliyandebug.libraryapi.domain.repository.BookRepository;
import oliyandebug.libraryapi.service.BookService;
import oliyandebug.libraryapi.service.exception.BusinessException;
import oliyandebug.libraryapi.service.exception.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.Optional.ofNullable;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository bookRepo){
        this.repository = bookRepo;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Book> findAllBorrowed() {
        return repository.findAllByBorrowedTrue();
    }
    @Transactional(readOnly = true)
    public List<Book> findAllAvailable() {
        return repository.findAllByBorrowedFalse();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
    @Override
    @Transactional
    public Book create(Book bookToAdd) {
        ofNullable(bookToAdd).orElseThrow(() -> new BusinessException("Book must be not null"));
        ofNullable(bookToAdd.getTitle()).orElseThrow(() -> new BusinessException("Book title is needed to add a new book"));
        ofNullable(bookToAdd.getIsbn()).orElseThrow(() -> new BusinessException("Book isbn is needed to add a new book"));
        if(repository.existsBookByIsbn(bookToAdd.getIsbn())) throw  new BusinessException("Book already in the database");
        return repository.save(bookToAdd);
    }

    @Override
    @Transactional
    public Book update(Long id, Book bookToUpdate) {
        var bookDb = this.findById(id);
        bookDb.setAuthor(bookToUpdate.getAuthor());
        bookDb.setTitle(bookToUpdate.getTitle());
        bookDb.setIsbn(bookToUpdate.getIsbn());
        return repository.save(bookDb);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var bookToDelete = this.findById(id);
        repository.delete(bookToDelete);
    }

    public boolean bookIsBorrowed(long id){
        return repository.existsBookByIdAndBorrowedTrue(id);
    }
    public boolean bookIsAvailable(long id){
        return repository.existsBookByIdAndBorrowedFalse(id);
    }
}
