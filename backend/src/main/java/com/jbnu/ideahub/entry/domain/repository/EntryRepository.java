package com.jbnu.ideahub.entry.domain.repository;

import com.jbnu.ideahub.entry.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
