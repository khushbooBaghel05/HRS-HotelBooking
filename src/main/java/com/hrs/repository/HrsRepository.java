package com.hrs.repository;

import org.springframework.data.repository.CrudRepository;

import com.hrs.model.Bookings;
/**
 * Repository interface for managing {@link Bookings} entities.
 * Extends {@link CrudRepository} to provide CRUD operations.
 */
public interface HrsRepository extends CrudRepository<Bookings, Long> {

}
