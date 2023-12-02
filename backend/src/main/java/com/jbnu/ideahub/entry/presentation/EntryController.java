package com.jbnu.ideahub.entry.presentation;

import com.jbnu.ideahub.common.dto.ApiResponse;
import com.jbnu.ideahub.entry.dto.request.EntryCreateRequest;
import com.jbnu.ideahub.entry.dto.request.EntryUpdateRequest;
import com.jbnu.ideahub.entry.dto.response.EntryResponse;
import com.jbnu.ideahub.entry.application.EntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    public ResponseEntity<Void> createEntry(
            @RequestBody @Valid final EntryCreateRequest request
    ) {
        final Long entryId = entryService.save(request.getCompetitionId(), request);
        return ResponseEntity.created(URI.create("/entries/" + entryId)).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EntryResponse>>> getEntries(
            @PageableDefault(
                    page = 0,
                    size = 6,
                    sort = "datetimeMetadata.createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        Page<EntryResponse> response = entryService.findAll(pageable);
        return ResponseEntity.ok().body(new ApiResponse<>(response));
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<ApiResponse<EntryResponse>> getEntryById(
            @PathVariable final Long entryId
    ) {
        final EntryResponse entryResponse = entryService.findById(entryId);
        return ResponseEntity.ok(new ApiResponse<>(entryResponse));
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<Void> updateEntry(
            @PathVariable final Long entryId,
            @RequestBody @Valid EntryUpdateRequest entryUpdateRequest
    ) {
        entryService.update(entryId, entryUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> deleteEntry(
            @PathVariable final Long entryId
    ) {
        entryService.delete(entryId);
        return ResponseEntity.noContent().build();
    }
}
