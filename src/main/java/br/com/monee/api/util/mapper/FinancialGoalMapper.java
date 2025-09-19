package br.com.monee.api.util.mapper;

import br.com.monee.api.domain.financialGoal.FinancialGoalEntity;
import br.com.monee.api.domain.financialGoal.FinancialGoalRequestDTO;
import br.com.monee.api.domain.financialGoal.FinancialGoalResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialGoalMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    FinancialGoalEntity requestToEntity (FinancialGoalRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    FinancialGoalEntity responseToEntity (FinancialGoalResponseDTO dto);

    FinancialGoalRequestDTO entityToRequest (FinancialGoalEntity entity);

    FinancialGoalResponseDTO entityToResponse (FinancialGoalEntity entity);
}
