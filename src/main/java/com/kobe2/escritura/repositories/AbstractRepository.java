package com.kobe2.escritura.repositories;

import com.kobe2.escritura.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbstractRepository extends JpaRepository<AbstractEntity, UUID> {
}
