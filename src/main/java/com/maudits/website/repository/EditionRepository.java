package com.maudits.website.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.maudits.website.repository.entities.Edition;

public interface EditionRepository extends CrudRepository<Edition, Long> {

	Edition findOneByCurrentTrue();

	Edition findOneByNextTrue();

	List<Edition> findAllByCurrentFalseAndNextFalse();

}
