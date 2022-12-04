package com.cosmere.service;

import java.util.List;
import java.util.Optional;

import com.cosmere.dto.CosmereCharacterDTO;
import com.cosmere.entity.CosmereCharacter;
import com.cosmere.exception.CosmereCharacterException;

public interface CharacterService {

	public CosmereCharacterDTO createCharacter(CosmereCharacterDTO character);
	public CosmereCharacterDTO findById(Long characterId) throws CosmereCharacterException;
	public boolean delete(Long chracterId) throws CosmereCharacterException;
	public List<CosmereCharacterDTO> findByName(String characterName);
	
}
