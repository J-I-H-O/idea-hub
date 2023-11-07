package com.jbnu.ideahub.competition.presentation;

import com.jbnu.ideahub.common.dto.ApiResponse;
import com.jbnu.ideahub.competition.dto.request.CompetitionRequest;
import com.jbnu.ideahub.competition.dto.response.CompetitionResponse;
import com.jbnu.ideahub.competition.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<Void> createCompetition(
            @RequestBody @Valid final CompetitionRequest competitionRequest
    ) {
        final Long competitionId = competitionService.save(competitionRequest);
        return ResponseEntity.created(URI.create("/competitions/" + competitionId)).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompetitionResponse>>> getCompetitions() {
        final List<CompetitionResponse> competitionResponses = competitionService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(competitionResponses));
    }

    @GetMapping("/{competitionId}")
    public ResponseEntity<ApiResponse<CompetitionResponse>> getCompetitionById(
            @PathVariable final Long competitionId
    ) {
        final CompetitionResponse competitionResponse = competitionService.findById(competitionId);
        return ResponseEntity.ok(new ApiResponse<>(competitionResponse));
    }

    @PutMapping("/{competitionId}")
    public ResponseEntity<Void> updateCompetition(
            @PathVariable final Long competitionId,
            @RequestBody @Valid CompetitionRequest competitionRequest
    ) {
        competitionService.update(competitionId, competitionRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity<Void> deleteCompetition(
            @PathVariable final Long competitionId
    ) {
        competitionService.delete(competitionId);
        return ResponseEntity.noContent().build();
    }
}
