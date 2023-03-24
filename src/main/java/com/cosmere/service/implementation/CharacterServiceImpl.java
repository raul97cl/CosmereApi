package com.cosmere.service.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmere.dto.CosmereCharacterDTO;
import com.cosmere.entity.CosmereCharacter;
import com.cosmere.exception.CosmereCharacterException;
import com.cosmere.repository.CharacterRepository;
import com.cosmere.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {

	private static Logger logger = LogManager.getLogger(CharacterServiceImpl.class);
	
	@Autowired
	public CharacterRepository characterRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CosmereCharacterDTO createCharacter(CosmereCharacterDTO character) {
		
		CosmereCharacter newCharacter = this.mapper.map(character, CosmereCharacter.class);
		
		final CosmereCharacter savedCharacter = this.characterRepo.save(newCharacter);
		logger.info("Cosmere character created: [{}]", newCharacter);
		
		return this.mapper.map(savedCharacter, CosmereCharacterDTO.class);
	}

	@Override
	public CosmereCharacterDTO findById(Long characterId) throws CosmereCharacterException {
		
		logger.info(String.format("Finding character with id [%d]", characterId));
		final CosmereCharacter newCharacter = this.characterRepo.findById(characterId).orElseThrow(() -> new CosmereCharacterException(
						String.format("Character with id [%d] has not been found", characterId)));

		return this.mapper.map(newCharacter, CosmereCharacterDTO.class);
	}

	@Override
	public List<CosmereCharacterDTO> findByName(String characterName){
		
		logger.info(String.format("Finding characters with name [%s]", characterName));
		final List<CosmereCharacter> charactersFound = this.characterRepo.findByNameLike(characterName);

		List<CosmereCharacterDTO> characters = charactersFound.stream().map(character -> this.mapper.map(character, CosmereCharacterDTO.class)).collect(Collectors.toList());

		return characters;
	}

	@Override
	public boolean delete(Long characterId) throws CosmereCharacterException {

		logger.info(String.format("Deleting character with id [%d]", characterId));
		final CosmereCharacter characterFound = this.characterRepo.findById(characterId).orElseThrow(() -> new CosmereCharacterException(
				String.format("Character with id [%d] has not been found", characterId)));
		
		this.characterRepo.deleteById(characterFound.getId());
		
		return !this.characterRepo.findById(characterId).isPresent();
	}

}
