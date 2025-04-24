package com.purihuaman.mapper;

import com.purihuaman.dto.LoanDTO;
import com.purihuaman.model.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {
	@Mapping(target = "book.id", source = "bookId")
	@Mapping(target = "customer.id", source = "customerId")
	LoanEntity toEntity(final LoanDTO loanDTO);

	@Mapping(target = "bookId", source = "book.id")
	@Mapping(target = "customerId", source = "customer.id")
	LoanDTO toDTO(final LoanEntity loanEntity);

	List<LoanDTO> toDTOList(final List<LoanEntity> loanEntities);
}
