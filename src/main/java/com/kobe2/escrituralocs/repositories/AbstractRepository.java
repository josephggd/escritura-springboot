package com.kobe2.escrituralocs.repositories;

import com.kobe2.escrituralocs.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbstractRepository extends JpaRepository<AbstractEntity, UUID> {
}
