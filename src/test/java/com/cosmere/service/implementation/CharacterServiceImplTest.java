package com.cosmere.service.implementation;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;

import com.cosmere.configuration.ModelMapperConfig;
import com.cosmere.dto.CosmereCharacterDTO;
import com.cosmere.entity.CosmereCharacter;
import com.cosmere.exception.CosmereCharacterException;
import com.cosmere.repository.CharacterRepository;

@ContextConfiguration(classes = ModelMapperConfig.class)
class CharacterServiceImplTest {

	@Mock
	private CharacterRepository characterRepository;
	
	private ModelMapper modelMappper;
	
	private CharacterServiceImpl characterService;
	
	List<CosmereCharacter> characters;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.modelMappper = new ModelMapper();
		this.characterService = new CharacterServiceImpl(this.modelMappper, this.characterRepository);
		
		this.characters = new ArrayList<CosmereCharacter>();
		this.characters.add(new CosmereCharacter(1L, "Dalinar", new Date(), new Date(), "Alive", null, "a"));
		this.characters.add(new CosmereCharacter(2L, "Shallan", new Date(), new Date(), "Alive", null, "a"));
		this.characters.add(new CosmereCharacter(3L, "Dalinar", new Date(), new Date(), "Alive", null, "a"));
	}
	
	@Test
	void findByNameTest() {
		when(this.characterRepository.findByNameContaining("Dalinar")).thenReturn(characters.stream().filter(character -> character.getName().contains("Dalinar")).collect(Collectors.toList()));
		Assertions.assertTrue(this.characterService.findByName("Dalinar").size() == 2);
	}
	
	@Test
	void findByIdTest() throws CosmereCharacterException {
		when(this.characterRepository.findById(2L)).thenReturn(Optional.ofNullable(this.characters.get(1)));
		CosmereCharacterDTO characterFound = this.characterService.findById(2L);;
		
		Assertions.assertTrue(characterFound.getId() == 2L);
		Assertions.assertEquals(characterFound.getName(), "Shallan");
	}

}
