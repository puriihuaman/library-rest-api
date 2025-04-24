package com.purihuaman.mapper;

import com.purihuaman.dto.PublisherDTO;
import com.purihuaman.model.PublisherEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
	PublisherDTO toDTO(PublisherEntity publisherEntity);

	PublisherEntity toEntity(PublisherDTO publisherDTO);

	List<PublisherEntity> toEntityList(List<PublisherDTO> publisherDTOList);

	List<PublisherDTO> toDTOList(List<PublisherEntity> publisherEntities);
}
