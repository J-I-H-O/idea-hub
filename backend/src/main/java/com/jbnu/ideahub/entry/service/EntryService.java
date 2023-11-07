package com.jbnu.ideahub.entry.service;

import com.jbnu.ideahub.entry.dto.request.EntryRequest;
import com.jbnu.ideahub.entry.dto.response.EntryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    public Long save(EntryRequest entryRequest) {
        return null;
    }

    public List<EntryResponse> findAll() {
        return null;
    }

    public EntryResponse findById(Long entryId) {
        return null;
    }

    public void update(Long entryId, EntryRequest entryRequest) {

    }

    public void delete(Long entryId) {

    }
}
