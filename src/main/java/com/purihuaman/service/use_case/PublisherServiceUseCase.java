package com.purihuaman.service.use_case;

import com.purihuaman.dto.PublisherDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PublisherServiceUseCase {
    List<PublisherDTO> findAllPublishers(Pageable page);
    
    PublisherDTO findPublisherById(UUID publisherId);
    
    PublisherDTO createPublisher(PublisherDTO publisher);
    
    PublisherDTO updatePublisher(UUID publisherId, PublisherDTO publisher);
    
    void deletePublisher(UUID publisherId);
    
    List<PublisherDTO> filterPublishers(Map<String, String> valuesToFilter, Pageable page);
}
