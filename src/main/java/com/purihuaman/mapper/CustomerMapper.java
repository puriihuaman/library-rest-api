package com.purihuaman.mapper;

import com.purihuaman.dto.CustomerDTO;
import com.purihuaman.model.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerEntity toEntity(CustomerDTO customerDTO);

	CustomerDTO toDTO(CustomerEntity customerEntity);

	List<CustomerDTO> toDTOList(List<CustomerEntity> customerEntities);
}
