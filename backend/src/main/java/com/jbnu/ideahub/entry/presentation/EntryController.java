package com.jbnu.ideahub.entry.presentation;

import com.jbnu.ideahub.common.dto.ApiResponse;
import com.jbnu.ideahub.entry.dto.request.EntryRequest;
import com.jbnu.ideahub.entry.dto.response.EntryResponse;
import com.jbnu.ideahub.entry.service.EntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    public ResponseEntity<Void> createEntry(
            @RequestBody @Valid final EntryRequest entryRequest
    ) {
        final Long entryId = entryService.save(entryRequest);
        return ResponseEntity.created(URI.create("/entries/" + entryId)).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EntryResponse>>> getEntries() {
        List<EntryResponse> entryResponses = entryService.findAll();
        return ResponseEntity.ok().body(new ApiResponse<>(entryResponses));
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
            @RequestBody @Valid EntryRequest entryRequest
    ) {
        entryService.update(entryId, entryRequest);
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
