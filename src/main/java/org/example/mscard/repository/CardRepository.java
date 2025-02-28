package org.example.mscard.repository;

import org.example.mscard.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

	Optional<CardEntity> findCardEntityByAccountId(Long userId);

	Optional<CardEntity> findCardEntityByUserId(Long userId);

}