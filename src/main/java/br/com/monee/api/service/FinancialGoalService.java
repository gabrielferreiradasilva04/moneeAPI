package br.com.monee.api.service;

import br.com.monee.api.util.mapper.FinancialGoalMapper;
import br.com.monee.api.domain.financialGoal.FinancialGoalEntity;
import br.com.monee.api.domain.user.UserEntity;
import br.com.monee.api.domain.financialGoal.FinancialGoalRequestDTO;
import br.com.monee.api.domain.financialGoal.FinancialGoalResponseDTO;
import br.com.monee.api.repository.FinancialGoalRepository;
import org.springframework.stereotype.Service;

@Service
public class FinancialGoalService {
    private final FinancialGoalRepository financialGoalRepository;
    private final FinancialGoalMapper financialGoalMapper;
    private final UserService userService;
    public FinancialGoalService(FinancialGoalRepository financialGoalRepository, FinancialGoalMapper financialGoalMapper, UserService userService) {
        this.financialGoalRepository = financialGoalRepository;
        this.financialGoalMapper = financialGoalMapper;
        this.userService = userService;
    }

    public FinancialGoalResponseDTO save(FinancialGoalRequestDTO dto) {
        FinancialGoalEntity financialGoalEntity = this.financialGoalMapper.requestToEntity(dto);
        UserEntity user = this.userService.getUserByUUID(dto.userId());
        financialGoalEntity.setUser(user);

        return this.financialGoalMapper.entityToResponse(this.financialGoalRepository.save(financialGoalEntity));

    }
}
