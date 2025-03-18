package com.purihuaman.mapper;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.model.AuthorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
	AuthorEntity toEntity(AuthorDTO authorDTO);

	AuthorDTO toDTO(AuthorEntity authorEntity);

	List<AuthorEntity> toEntityList(List<AuthorDTO> authorDTOList);

	List<AuthorDTO> toDTOList(List<AuthorEntity> authorEntities);
}
