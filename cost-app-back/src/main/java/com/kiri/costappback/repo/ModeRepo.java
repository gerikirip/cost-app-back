package com.kiri.costappback.repo;

import com.kiri.costappback.model.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeRepo extends JpaRepository<Mode, Long> {
    Optional<Mode> findModeyByName(String name);
}
