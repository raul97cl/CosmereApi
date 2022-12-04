package com.cosmere.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosmere.dto.WorldDTO;
import com.cosmere.exception.CosmereCharacterException;
import com.cosmere.exception.WorldException;
import com.cosmere.service.implementation.WorldServiceImpl;

@Controller
@RequestMapping("/api/world")
public class WorldController {

	@Autowired
	private WorldServiceImpl worldService;
	
	@PostMapping("/create")
	public ResponseEntity<WorldDTO> create(@RequestBody WorldDTO world){
		
		final WorldDTO worldSaved = this.worldService.save(world);
		
		return ResponseEntity.ok(worldSaved);
	}
	
	@GetMapping("/{worldId}")
	public ResponseEntity<WorldDTO> findByid(@PathVariable Long worldId) throws WorldException{
		
		final WorldDTO world = this.worldService.findById(worldId);
		
		return ResponseEntity.ok(world);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<WorldDTO>> findbyName(@RequestParam("name") String name){
		
		final Set<WorldDTO> worlds = this.worldService.findByNameLike(name);
		
		return new ResponseEntity<>(worlds, !worlds.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{worldId}/addCharacter/{characterId}")
	public ResponseEntity<WorldDTO> addExistingCharacterToWorld(@PathVariable Long worldId, @PathVariable Long characterId) throws WorldException, CosmereCharacterException{
		
		final WorldDTO worldSaved = this.worldService.addExistingCharacterToWorld(worldId, characterId);
		
		return ResponseEntity.ok(worldSaved);
	}
	
	
	@DeleteMapping("/delete/{worldId}")
	public ResponseEntity<Boolean> delete(@RequestBody Long world) throws WorldException{
		
		final boolean isDeleted = this.worldService.deleteById(world);
		
		return new ResponseEntity<>(isDeleted, isDeleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({WorldException.class, RuntimeException.class})
	public ResponseEntity<String> controlWorldException(Exception worldException){
		return new ResponseEntity<>(worldException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
