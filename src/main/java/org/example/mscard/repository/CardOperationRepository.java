package org.example.mscard.repository;

import org.example.mscard.entity.CardOperationInKafkaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardOperationRepository extends JpaRepository<CardOperationInKafkaEntity, Long> {
}
