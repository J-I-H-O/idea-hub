package com.jbnu.ideahub.entry.service;

import com.jbnu.ideahub.entry.dto.request.EntryCreateRequest;
import com.jbnu.ideahub.entry.dto.request.EntryUpdateRequest;
import com.jbnu.ideahub.entry.dto.response.EntryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    public Long save(EntryCreateRequest entryCreateRequest) {
        return null;
    }

    public List<EntryResponse> findAll() {
        return null;
    }

    public EntryResponse findById(Long entryId) {
        return null;
    }

    public void update(Long entryId, EntryUpdateRequest entryUpdateRequest) {

    }

    public void delete(Long entryId) {

    }
}
