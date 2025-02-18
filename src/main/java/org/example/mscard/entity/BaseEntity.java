package org.example.mscard.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime created;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime lastUpdated;
}
