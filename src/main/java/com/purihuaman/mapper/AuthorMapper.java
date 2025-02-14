package com.purihuaman.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.purihuaman.dto.AuthorDTO;
import com.purihuaman.entity.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
	Author toAuthor(AuthorDTO author);

	AuthorDTO toAuthorDTO(Author author);

	List<Author> toAuthorList(List<AuthorDTO> authors);

	List<AuthorDTO> toAuthorDTOList(List<Author> authors);
}
