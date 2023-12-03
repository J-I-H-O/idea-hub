package com.jbnu.ideahub.entry.domain.repository;

import com.jbnu.ideahub.entry.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
