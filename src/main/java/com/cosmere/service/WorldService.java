package com.cosmere.service;

import java.util.Optional;
import java.util.Set;

import com.cosmere.dto.CosmereCharacterDTO;
import com.cosmere.dto.WorldDTO;
import com.cosmere.exception.CosmereCharacterException;
import com.cosmere.exception.WorldException;

public interface WorldService {

	public WorldDTO save(WorldDTO world);
	public WorldDTO findById(Long worldId) throws WorldException;
	public Set<WorldDTO> findByNameLike(String name);
	public boolean deleteById(Long worldId) throws WorldException;
	public WorldDTO addExistingCharacterToWorld(Long worldId, Long characterId) throws WorldException, CosmereCharacterException;
}
