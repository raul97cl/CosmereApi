package com.cosmere.service.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmere.dto.CosmereCharacterDTO;
import com.cosmere.dto.WorldDTO;
import com.cosmere.entity.CosmereCharacter;
import com.cosmere.entity.World;
import com.cosmere.exception.CosmereCharacterException;
import com.cosmere.exception.WorldException;
import com.cosmere.repository.CharacterRepository;
import com.cosmere.repository.WorldRepository;
import com.cosmere.service.WorldService;

@Service
public class WorldServiceImpl implements WorldService {

	private static Logger logger = LogManager.getLogger(WorldServiceImpl.class);
	
	@Autowired
	private WorldRepository worldRepo;
	
	@Autowired
	public CharacterRepository characterRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public WorldDTO save(WorldDTO world) {

		World newWorld = this.mapper.map(world, World.class);
		
		final World worldSaved = this.worldRepo.save(newWorld);
		logger.info("Cosmere world created: [{}]", newWorld);
		
		return this.mapper.map(worldSaved, WorldDTO.class);
	}

	@Override
	public WorldDTO findById(Long worldId) throws WorldException {
		
		logger.info(String.format("Finding world with id [%d]", worldId));
		final World foundWorld = this.worldRepo.findById(worldId).orElseThrow(() -> new WorldException(
				String.format("World with id [%d] has not been found", worldId)));
			
		return this.mapper.map(foundWorld, WorldDTO.class);
	}

	@Override
	public Set<WorldDTO> findByNameLike(String name) {

		logger.info(String.format("Finding worlds with id [%s]", name));
		final Set<World> foundWorlds = this.worldRepo.findbyNameLike(name);
		
		Set<WorldDTO> worlds = foundWorlds.stream().map(world -> this.mapper.map(world, WorldDTO.class)).collect(Collectors.toSet());
		
		return worlds;
	}
	
	@Override
	public WorldDTO addExistingCharacterToWorld(Long worldId, Long characterId) throws WorldException, CosmereCharacterException {
		
		logger.info(String.format("Finding world with id [%d]", worldId));
		World world = this.worldRepo.findById(worldId).orElseThrow(() -> new WorldException(
				String.format("World with id [%d] has not been found", worldId)));
		
		logger.info(String.format("Finding character with id [%d]", characterId));
		CosmereCharacter newCharacter = this.characterRepo.findById(characterId).orElseThrow(() -> new CosmereCharacterException(
						String.format("Character with id [%d] has not been found", characterId)));
		
		newCharacter.setOriginWorld(world);
		world.getCharacters().add(newCharacter);
		this.worldRepo.save(world);
		
		return this.mapper.map(world, WorldDTO.class);
	}

	@Override
	public boolean deleteById(Long worldId) throws WorldException {

		logger.info(String.format("Deleting world with id [%d]", worldId));
		final World foundWorld = this.worldRepo.findById(worldId).orElseThrow(() -> new WorldException(
				String.format("World with id [%d] has not been found", worldId)));
		
		this.worldRepo.deleteById(foundWorld.getId());
		
		return !this.worldRepo.findById(worldId).isPresent();
	}

}
