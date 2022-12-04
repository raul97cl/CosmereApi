package com.cosmere.dto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class WorldDTO {

	private Long id;
	private String name;
	private Set<String> perpendicularities;
	private Set<String> investitures;
	private String system;
	@JsonIgnore
	private Set<CosmereCharacterDTO> characters  = new HashSet<>();
	private String history;

	public WorldDTO() {
		super();
	}

	public WorldDTO(Long id, String name, Set<String> perpendicularities, Set<String> investitures, String system,
			Set<CosmereCharacterDTO> characters, String history) {
		super();
		this.id = id;
		this.name = name;
		this.perpendicularities = perpendicularities;
		this.investitures = investitures;
		this.system = system;
		this.characters = characters;
		this.history = history;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getPerpendicularities() {
		return perpendicularities;
	}

	public void setPerpendicularities(Set<String> perpendicularities) {
		this.perpendicularities = perpendicularities;
	}

	public Set<String> getInvestitures() {
		return investitures;
	}

	public void setInvestitures(Set<String> investitures) {
		this.investitures = investitures;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Set<CosmereCharacterDTO> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<CosmereCharacterDTO> characters) {
		this.characters = characters;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	@Override
	public String toString() {
		List<Field> properties = Arrays.asList(this.getClass().getDeclaredFields());
		ToStringBuilder stringBuilder = new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE);
		
		properties.stream().forEach(field -> {
			try {
				stringBuilder.append(field.getName(), field.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		
		return stringBuilder.toString();
	}
}
