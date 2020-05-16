package com.nvrsk.repository;

import com.nvrsk.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link PriceHistory} entities to perform base operations.
 */
@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
}
