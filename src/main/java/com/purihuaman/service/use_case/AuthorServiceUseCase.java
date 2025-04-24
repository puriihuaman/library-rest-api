package com.purihuaman.service.use_case;

import com.purihuaman.dto.AuthorDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AuthorServiceUseCase {
    List<AuthorDTO> findAllAuthors(Pageable page);
    
    AuthorDTO findAuthorById(UUID authorId);
    
    AuthorDTO createAuthor(AuthorDTO author);
    
    AuthorDTO updateAuthor(UUID authorId, AuthorDTO author);
    
    void deleteAuthor(UUID authorId);
    
    List<AuthorDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page);
}
