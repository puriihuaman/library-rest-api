package com.purihuaman.mapper;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.model.AuthorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
	AuthorDTO toDTO(AuthorEntity authorEntity);

	AuthorEntity toEntity(AuthorDTO authorDTO);

	List<AuthorDTO> toDTOList(List<AuthorEntity> authorEntities);

	List<AuthorEntity> toEntityList(List<AuthorDTO> authorDTOList);
}
