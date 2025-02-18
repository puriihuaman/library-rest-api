package com.purihuaman.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.purihuaman.dto.EditorialDTO;
import com.purihuaman.entity.Editorial;

@Mapper(componentModel = "spring")
public interface EditorialMapper {
	Editorial toEditorial(EditorialDTO editorial);

	EditorialDTO toEditorialDTO(Editorial editorial);

	List<Editorial> toEditorialList(List<EditorialDTO> editorials);

	List<EditorialDTO> toEditorialDTOList(List<Editorial> editorials);
}
