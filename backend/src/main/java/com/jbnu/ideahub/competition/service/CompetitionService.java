package com.jbnu.ideahub.competition.service;

import com.jbnu.ideahub.competition.dto.request.CompetitionRequest;
import com.jbnu.ideahub.competition.dto.response.CompetitionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {
    public Long save(CompetitionRequest competitionRequest) {
        return 1L;
    }

    public List<CompetitionResponse> findAll() {

        return null;
    }

    public CompetitionResponse findById(Long competitionId) {
        return null;
    }

    public void update(Long competitionId, CompetitionRequest competitionRequest) {

    }

    public void delete(Long competitionId) {

    }
}
