package com.purihuaman.service;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.dto.BookDTO;
import com.purihuaman.dto.PublisherDTO;
import com.purihuaman.mapper.AuthorMapper;
import com.purihuaman.mapper.BookMapper;
import com.purihuaman.mapper.PublisherMapper;
import com.purihuaman.model.BookEntity;
import com.purihuaman.repository.BookRepository;
import com.purihuaman.repository.PublisherRepository;
import com.purihuaman.service.use_case.BookServiceUseCase;
import com.purihuaman.utils.BookSpecification;
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
	private final PublisherRepository publisherRepository;
	private final AuthorMapper authorMapper;
	private final PublisherMapper publisherMapper;
	private final PublisherService publisherService;

	public BookService(
		BookRepository bookRepository,
		BookMapper bookMapper,
		AuthorService authorService,
		PublisherRepository publisherRepository,
		AuthorMapper authorMapper,
		PublisherMapper publisherMapper,
		PublisherService publisherService
	)
	{
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
		this.authorService = authorService;
		this.publisherRepository = publisherRepository;
		this.authorMapper = authorMapper;
		this.publisherMapper = publisherMapper;
		this.publisherService = publisherService;
	}

	@Override
	public List<BookDTO> findAllBooks(Pageable page) {
		return bookMapper.toDTOList(bookRepository.findAll(page).getContent());
	}

	@Override
	public BookDTO findBookById(UUID id) {
		Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);

		if (bookEntityOptional.isEmpty()) {
			throw new RuntimeException("Book not found");
		}

		return bookMapper.toDTO(bookEntityOptional.get());
	}

	@Override
	public BookDTO createBook(BookDTO bookDTO) {
		// Obtener el id del author y publisher y buscar
		UUID authorId = bookDTO.getAuthorId();
		System.out.println("----");
		System.out.println(bookDTO.getTitle());
		System.out.println(bookDTO.getIsbn());
		System.out.println(bookDTO.getTotalCopies());
		System.out.println("AUTHOR: " + bookDTO.getAuthorId());
		System.out.println("PUBLISHER: " + bookDTO.getPublisherId());
		System.out.println("---------");
		//Optional<AuthorEntity> author = authorRepository.findById(authorId);
		AuthorDTO author = authorService.findAuthorById(authorId);

		System.out.println("--------- AUTHOR DB --------");
		System.out.println(author.getId());
		System.out.println(author.getFirstName());
		System.out.println(author.getEmail());
		System.out.println("-----------------");

		// Validar que exista el author
		//if (author.isEmpty()) {
		//	throw new RuntimeException("Author not found");
		//}

		UUID publisherId = bookDTO.getPublisherId();
		System.out.println("--- ID: " + publisherId);
		PublisherDTO publisher = publisherService.findPublisherById(publisherId);
		// Validar que exista el publisher
		//if (publisher.isEmpty()) {
		//	throw new RuntimeException("Publisher not found");
		//}
		//PublisherDTO _publisher = publisherMapper.toDTO(publisher.getId());
		System.out.println("--------- PUBLISHER DB --------");
		System.out.println(publisher.getId());
		System.out.println(publisher.getName());
		System.out.println("-----------------");

		BookDTO
			bookEntity =
			BookDTO.builder().isbn(bookDTO.getIsbn()).title(bookDTO.getTitle()).publicationDate(
				bookDTO.getPublicationDate()).totalCopies(bookDTO.getTotalCopies()).borrowedCount(
				bookDTO.getBorrowedCount()).availableCopies(bookDTO.getTotalCopies()).authorId(
				author.getId()).publisherId(publisher.getId()).build();

		//bookDTO.setPublisher(publisherMapper.toEntity(publisherMapper.toDTO(publisher.get())));
		//System.out.println("********");
		//System.out.println(author.getFirstName());
		//System.out.println(publisher.get().getName());
		//System.out.println("********");
		//bookDTO.setAvailableCopies(bookDTO.getTotalCopies());
		BookEntity bookEntityToSave = bookMapper.toEntity(bookEntity);

		return bookMapper.toDTO(bookRepository.save(bookEntityToSave));
	}

	@Override
	public BookDTO updateBook(UUID id, BookDTO book) {
		BookDTO bookDTOFound = this.findBookById(id);

		// Search author by id
		AuthorDTO author = authorService.findAuthorById(book.getAuthorId());
		// Search publisher by id
		PublisherDTO publisher = publisherService.findPublisherById(book.getPublisherId());

		bookDTOFound.setIsbn(book.getIsbn());
		bookDTOFound.setTitle(book.getTitle());
		bookDTOFound.setPublicationDate(book.getPublicationDate());
		bookDTOFound.setTotalCopies(book.getTotalCopies());
		bookDTOFound.setBorrowedCount(book.getBorrowedCount());
		bookDTOFound.setAvailableCopies(book.getAvailableCopies());
		bookDTOFound.setAuthorId(author.getId());
		bookDTOFound.setPublisherId(publisher.getId());

		BookEntity updatedBook = bookRepository.save(bookMapper.toEntity(bookDTOFound));
		return bookMapper.toDTO(updatedBook);
	}

	@Override
	public void deleteBook(UUID id) {
		BookDTO bookFound = this.findBookById(id);
		bookRepository.deleteById(bookFound.getId());
	}

	@Override
	public List<BookDTO> filterBooks(Map<String, String> valuesToFilter, Pageable page) {
		Specification<BookEntity> spec = BookSpecification.filterBooks(valuesToFilter);
		List<BookEntity> books = bookRepository.findAll(spec, page).getContent();

		return bookMapper.toDTOList(books);
	}

	@Override
	public void updateBorrowedBookCount(UUID id, Integer amount) {
		BookDTO bookFound = this.findBookById(id);

		if (bookFound.getAvailableCopies() <= 0) {
			throw new RuntimeException("Not available copies");
		}

		System.out.println("---------");
		System.out.println("--- ID: " + id);
		System.out.println("--- ID: " + bookFound.getId());
		System.out.println("TITLE: " + bookFound.getTitle());
		System.out.println("BORROWED: " + bookFound.getBorrowedCount());
		System.out.println("TOTAL COPIES: " + bookFound.getTotalCopies());
		System.out.println("---------");
		// Update
		bookFound.setBorrowedCount(bookFound.getBorrowedCount() + amount);
		bookFound.setAvailableCopies(bookFound.getTotalCopies() - bookFound.getBorrowedCount());

		bookRepository.save(bookMapper.toEntity(bookFound));
	}
}
