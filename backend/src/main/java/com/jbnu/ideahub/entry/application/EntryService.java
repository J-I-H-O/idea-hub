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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntryService {

    private final EntryRepository entryRepository;
    private final CompetitionRepository competitionRepository;

    @Transactional
    public Long save(final Long competitionId, final EntryCreateRequest request) {
        Optional<Competition> competition = Optional.empty();
        if (competitionId != null) {
            competition = competitionRepository.findById(competitionId);
        }

        Entry saved = entryRepository.save(request.toEntity(competition.orElse(null), DatetimeMetadata.create()));
        return saved.getId();
    }

    public Page<EntryResponse> findAll(Pageable pageable) {
        return entryRepository.findAll(pageable)
                .map(EntryResponse::of);
    }

    public EntryResponse findById(Long entryId) {
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(NoSuchEntryException::new);

        return EntryResponse.of(entry);
    }

    @Transactional
    public void update(Long entryId, EntryUpdateRequest request) {
        Optional<Competition> competition = Optional.empty();
        if (request.getCompetitionId() != null) {
            competition = competitionRepository.findById(request.getCompetitionId());
        }

        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(NoSuchEntryException::new);

        entry.update(competition.orElse(null), request);
    }

    @Transactional
    public void delete(Long entryId) {
        entryRepository.deleteById(entryId);
    }
}
