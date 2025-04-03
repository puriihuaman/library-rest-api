package com.purihuaman.mapper;

import com.purihuaman.dto.BookDTO;
import com.purihuaman.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
	// destination(entity): target -> origin(dto): source
	@Mapping(target = "author.id", source = "authorId")
	@Mapping(target = "publisher.id", source = "publisherId")
	BookEntity toEntity(BookDTO bookDTO);

	// destination(dto): target -> origin(entity): source
	@Mapping(target = "authorId", source = "author.id")
	@Mapping(target = "publisherId", source = "publisher.id")
	BookDTO toDTO(BookEntity entity);

	List<BookEntity> toEntityList(List<BookDTO> bookDTOList);

	List<BookDTO> toDTOList(List<BookEntity> bookEntities);
}
