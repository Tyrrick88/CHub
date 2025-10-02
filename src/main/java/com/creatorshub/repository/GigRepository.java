package com.creatorshub.repository;

import com.creatorshub.model.Gig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GigRepository extends JpaRepository<Gig, Long> {
    List<Gig> findByOwnerId(Long ownerId);
}
