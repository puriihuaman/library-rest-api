package com.purihuaman.service;

import com.purihuaman.dto.PublisherDTO;
import com.purihuaman.enums.APIError;
import com.purihuaman.exception.APIRequestException;
import com.purihuaman.mapper.PublisherMapper;
import com.purihuaman.model.PublisherEntity;
import com.purihuaman.repository.PublisherRepository;
import com.purihuaman.service.use_case.PublisherServiceUseCase;
import com.purihuaman.utils.PublisherSpecification;
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
public class PublisherService implements PublisherServiceUseCase {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;
    
    public PublisherService(
        PublisherRepository publisherRepository,
        PublisherMapper publisherMapper
    )
    {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }
    
    @Override
    public List<PublisherDTO> findAllPublishers(Pageable page) {
        try {
            List<PublisherEntity>
                publisherEntities =
                publisherRepository.findAll(page).getContent();
            return publisherMapper.toDTOList(publisherEntities);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public PublisherDTO findPublisherById(UUID publisherId) {
        try {
            Optional<PublisherEntity> response = publisherRepository.findById(publisherId);
            
            if (response.isEmpty()) {
                APIError.RECORD_NOT_FOUND.setTitle("Publisher not found");
                APIError.RECORD_NOT_FOUND.setMessage(
                    "The publisher you are trying to access does not exist.");
                throw new APIRequestException(APIError.RECORD_NOT_FOUND);
            }
            PublisherEntity publisherFound = response.get();
            
            return publisherMapper.toDTO(publisherFound);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public PublisherDTO createPublisher(PublisherDTO publisher) {
        try {
            PublisherEntity publisherToSave = publisherMapper.toEntity(publisher);
            PublisherEntity savedPublisher = publisherRepository.save(publisherToSave);
            
            return publisherMapper.toDTO(savedPublisher);
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
    public PublisherDTO updatePublisher(UUID publisherId, PublisherDTO publisher) {
        try {
            PublisherDTO publisherFound = this.findPublisherById(publisherId);
            
            publisherFound.setName(publisher.getName());
            
            PublisherEntity publisherToUpdate = publisherMapper.toEntity(publisherFound);
            PublisherEntity updatedPublisher = publisherRepository.save(publisherToUpdate);
            
            return publisherMapper.toDTO(updatedPublisher);
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
    public void deletePublisher(UUID publisherId) {
        try {
            PublisherDTO publisherFound = this.findPublisherById(publisherId);
            publisherRepository.deleteById(publisherFound.getId());
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
    public List<PublisherDTO> filterPublishers(Map<String, String> valuesToFilter, Pageable page) {
        try {
            Specification<PublisherEntity> spec = PublisherSpecification.filterPublishers(
                valuesToFilter);
            
            List<PublisherEntity> publishers = publisherRepository.findAll(spec, page).getContent();
            
            return publisherMapper.toDTOList(publishers);
        } catch (DataAccessException ex) {
            throw new APIRequestException(APIError.DATABASE_ERROR);
        } catch (Exception ex) {
            throw new APIRequestException(APIError.INTERNAL_SERVER_ERROR);
        }
    }
}
