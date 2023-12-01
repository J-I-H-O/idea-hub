package com.jbnu.ideahub.entry.application;

import com.jbnu.ideahub.common.domain.DatetimeMetadata;
import com.jbnu.ideahub.competition.domain.Competition;
import com.jbnu.ideahub.competition.domain.repository.CompetitionRepository;
import com.jbnu.ideahub.entry.domain.Entry;
import com.jbnu.ideahub.entry.domain.repository.EntryRepository;
import com.jbnu.ideahub.entry.dto.request.EntryCreateRequest;
import com.jbnu.ideahub.entry.dto.request.EntryUpdateRequest;
import com.jbnu.ideahub.entry.dto.response.EntryResponse;
import com.jbnu.ideahub.entry.exception.NoSuchEntryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EntryService {

    private final EntryRepository entryRepository;
    private final CompetitionRepository competitionRepository;

    @Transactional
    public Long save(final Long competitionId, final EntryCreateRequest request) {
        Competition competition = competitionRepository.getById(competitionId);
        DatetimeMetadata datetimeMetadata = new DatetimeMetadata();

        Entry saved = entryRepository.save(request.toEntity(competition, datetimeMetadata));
        return saved.getId();
    }

    public List<EntryResponse> findAll() {
        return null;
    }

    public EntryResponse findById(Long entryId) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(NoSuchEntryException::new);

        return EntryResponse.of(entry);
    }

    @Transactional
    public void update(Long entryId, EntryUpdateRequest request) {
        Competition competition = null;
        if (request.getCompetitionId() != null) {
            competition = competitionRepository.getById(request.getCompetitionId());
        }
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(NoSuchEntryException::new);

        entry.update(competition, request);
    }

    @Transactional
    public void delete(Long entryId) {

    }
}
