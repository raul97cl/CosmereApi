package com.cosmere.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosmere.entity.CosmereCharacter;

@Repository
public interface CharacterRepository extends CrudRepository<CosmereCharacter, Long>{
	
	public CosmereCharacter save(CosmereCharacter character);
	
	public Optional<CosmereCharacter> findById(Long chracterId);
	
	public List<CosmereCharacter> findByNameContaining(@Param("name") String name);
	
	public void deleteById(Long chracterId);
	
}
