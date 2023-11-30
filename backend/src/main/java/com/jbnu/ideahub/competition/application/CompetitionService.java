package com.jbnu.ideahub.competition.application;

import com.jbnu.ideahub.competition.dto.request.CompetitionCreateRequest;
import com.jbnu.ideahub.competition.dto.request.CompetitionUpdateRequest;
import com.jbnu.ideahub.competition.dto.response.CompetitionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {
    public Long save(CompetitionCreateRequest competitionCreateRequest) {
        return 1L;
    }

    public List<CompetitionResponse> findAll() {

        return null;
    }

    public CompetitionResponse findById(Long competitionId) {
        return null;
    }

    public void update(Long competitionId, CompetitionUpdateRequest competitionUpdateRequest) {

    }

    public void delete(Long competitionId) {

    }
}
