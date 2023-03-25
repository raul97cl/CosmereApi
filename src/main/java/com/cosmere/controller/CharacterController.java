package com.cosmere.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmere.dto.CosmereCharacterDTO;
import com.cosmere.exception.CosmereCharacterException;
import com.cosmere.service.implementation.CharacterServiceImpl;

@RestController
@RequestMapping("/api/character")
public class CharacterController {
	
	@Autowired
	public CharacterServiceImpl characterService;

	@PostMapping("/create")
	public ResponseEntity<CosmereCharacterDTO> createCharacter(@RequestBody @Valid CosmereCharacterDTO character){
		
		final CosmereCharacterDTO newCharacter = this.characterService.createCharacter(character);
		
		return ResponseEntity.ok(newCharacter);
	}
	
	@GetMapping("/{characterId}")
	public ResponseEntity<CosmereCharacterDTO> findById(@PathVariable Long characterId) throws CosmereCharacterException {
		
		final CosmereCharacterDTO chracterFound = this.characterService.findById(characterId);;
		
		return ResponseEntity.ok(chracterFound);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CosmereCharacterDTO>> findbyName(@RequestParam("name") String name){
		
		final List<CosmereCharacterDTO> charactersFound = this.characterService.findByName(name);
		
		return new ResponseEntity<>(charactersFound, !charactersFound.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/{characterId}")
	public ResponseEntity<Boolean> delete(@PathVariable Long characterId) throws CosmereCharacterException{
		
		final boolean deleted = this.characterService.delete(characterId);
		
		return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({CosmereCharacterException.class, RuntimeException.class})
	public ResponseEntity<String> controlWorldException(Exception characterException){
		return new ResponseEntity<>(characterException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
