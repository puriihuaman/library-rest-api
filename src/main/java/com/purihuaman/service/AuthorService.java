package com.purihuaman.service;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.enums.APIError;
import com.purihuaman.exception.APIRequestException;
import com.purihuaman.mapper.AuthorMapper;
import com.purihuaman.model.AuthorEntity;
import com.purihuaman.repository.AuthorRepository;
import com.purihuaman.service.use_case.AuthorServiceUseCase;
import com.purihuaman.utils.AuthorSpecification;
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
public class AuthorService implements AuthorServiceUseCase {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }
    
    @Override
    public List<AuthorDTO> findAllAuthors(Pageable page) {
        try {
            List<AuthorEntity> authors = authorRepository.findAll(page).getContent();
            
            return authorMapper.toDTOList(authors);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public AuthorDTO findAuthorById(UUID authorId) {
        try {
            Optional<AuthorEntity> response = authorRepository.findById(authorId);
            
            if (response.isEmpty()) {
                APIError.RECORD_NOT_FOUND.setTitle("Author not found");
                APIError.RECORD_NOT_FOUND.setMessage(
                    "The author you are trying to access does not exist.");
                throw new APIRequestException(APIError.RECORD_NOT_FOUND);
            }
            AuthorEntity authorFound = response.get();
            
            return authorMapper.toDTO(authorFound);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public AuthorDTO createAuthor(AuthorDTO author) {
        try {
            AuthorEntity authorToSave = authorMapper.toEntity(author);
            AuthorEntity savedAuthor = authorRepository.save(authorToSave);
            
            return authorMapper.toDTO(savedAuthor);
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
    public AuthorDTO updateAuthor(UUID authorId, AuthorDTO author) {
        try {
            AuthorDTO authorFound = this.findAuthorById(authorId);
            
            authorFound.setFirstName(author.getFirstName());
            authorFound.setEmail(author.getEmail());
            
            AuthorEntity authorToUpdate = authorMapper.toEntity(authorFound);
            AuthorEntity updatedAuthor = authorRepository.save(authorToUpdate);
            
            return authorMapper.toDTO(updatedAuthor);
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
    public void deleteAuthor(UUID authorId) {
        try {
            AuthorDTO authorFound = this.findAuthorById(authorId);
            
            authorRepository.deleteById(authorFound.getId());
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
    public List<AuthorDTO> filterAuthors(Map<String, String> valuesToFilter, Pageable page) {
        try {
            Specification<AuthorEntity> spec = AuthorSpecification.filterAuthors(valuesToFilter);
            List<AuthorEntity> authors = authorRepository.findAll(spec, page).getContent();
            
            return authorMapper.toDTOList(authors);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
}
