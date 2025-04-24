package com.purihuaman.service;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.dto.BookDTO;
import com.purihuaman.dto.PublisherDTO;
import com.purihuaman.enums.APIError;
import com.purihuaman.exception.APIRequestException;
import com.purihuaman.mapper.BookMapper;
import com.purihuaman.model.BookEntity;
import com.purihuaman.repository.BookRepository;
import com.purihuaman.service.use_case.BookServiceUseCase;
import com.purihuaman.utils.BookSpecification;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService implements BookServiceUseCase {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    
    public BookService(
        BookRepository bookRepository,
        BookMapper bookMapper,
        AuthorService authorService,
        PublisherService publisherService
    )
    {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }
    
    @Override
    public List<BookDTO> findAllBooks(Pageable page) {
        try {
            List<BookEntity> books = bookRepository.findAll(page).getContent();
            
            return bookMapper.toDTOList(books);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public BookDTO findBookById(UUID bookId) {
        try {
            Optional<BookEntity> response = bookRepository.findById(bookId);
            
            if (response.isEmpty()) {
                APIError.RECORD_NOT_FOUND.setTitle("Book not found");
                APIError.RECORD_NOT_FOUND.setMessage(
                    "The book you are trying to access does not exist.");
                
                throw new APIRequestException(APIError.RECORD_NOT_FOUND);
            }
            BookEntity bookFound = response.get();
            
            return bookMapper.toDTO(bookFound);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public BookDTO createBook(BookDTO book) {
        try {
            UUID authorId = book.getAuthorId();
            AuthorDTO author = authorService.findAuthorById(authorId);
            
            UUID publisherId = book.getPublisherId();
            PublisherDTO publisher = publisherService.findPublisherById(publisherId);
            
            BookDTO
                bookEntity =
                BookDTO
                    .builder()
                    .isbn(book.getIsbn())
                    .title(book.getTitle())
                    .publicationDate(book.getPublicationDate())
                    .totalCopies(book.getTotalCopies())
                    .borrowedCount(book.getBorrowedCount())
                    .availableCopies(book.getTotalCopies())
                    .authorId(author.getId())
                    .publisherId(publisher.getId())
                    .build();
            
            BookEntity bookToSave = bookMapper.toEntity(bookEntity);
            BookEntity savedBook = bookRepository.save(bookToSave);
            
            return bookMapper.toDTO(savedBook);
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public BookDTO updateBook(UUID bookId, BookDTO book) {
        try {
            BookDTO bookFound = this.findBookById(bookId);
            
            AuthorDTO author = authorService.findAuthorById(book.getAuthorId());
            PublisherDTO publisher = publisherService.findPublisherById(book.getPublisherId());
            
            bookFound.setIsbn(book.getIsbn());
            bookFound.setTitle(book.getTitle());
            bookFound.setPublicationDate(book.getPublicationDate());
            bookFound.setTotalCopies(book.getTotalCopies());
            bookFound.setBorrowedCount(book.getBorrowedCount());
            bookFound.setAvailableCopies(book.getAvailableCopies());
            bookFound.setAuthorId(author.getId());
            bookFound.setPublisherId(publisher.getId());
            
            BookEntity bookToUpdate = bookMapper.toEntity(bookFound);
            BookEntity updatedBook = bookRepository.save(bookToUpdate);
            
            return bookMapper.toDTO(updatedBook);
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public void deleteBook(UUID bookId) {
        try {
            BookDTO bookFound = this.findBookById(bookId);
            bookRepository.deleteById(bookFound.getId());
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public List<BookDTO> filterBooks(Map<String, String> valuesToFilter, Pageable page) {
        try {
            Specification<BookEntity> spec = BookSpecification.filterBooks(valuesToFilter);
            List<BookEntity> books = bookRepository.findAll(spec, page).getContent();
            
            return bookMapper.toDTOList(books);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public void updateBorrowedBookCount(UUID bookId, Integer amount) {
        try {
            BookDTO bookFound = this.findBookById(bookId);
            
            if (bookFound.getAvailableCopies() <= 0) {
                APIError.RESOURCE_CONFLICT.setTitle("Not available copies");
                
                throw new APIRequestException(APIError.RESOURCE_CONFLICT);
            }
            
            bookFound.setBorrowedCount(bookFound.getBorrowedCount() + amount);
            bookFound.setAvailableCopies(bookFound.getTotalCopies() - bookFound.getBorrowedCount());
            
            bookRepository.save(bookMapper.toEntity(bookFound));
        } catch (APIRequestException ex) {
            throw ex;
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            Throwable rootCause = cause.getCause();
            
            if (cause instanceof ConstraintViolationException ||
                rootCause instanceof DataIntegrityViolationException) throw new APIRequestException(
                APIError.UNIQUE_CONSTRAINT_VIOLATION);
            else throw new APIRequestException(APIError.RESOURCE_ASSOCIATED_EXCEPTION);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
}
