package com.cosmere.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CosmereCharacter implements Serializable  {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "The name can not be blank")
	private String name;
	
	private Date birthDate;
	
	private Date deathDate;
	
	@NotBlank(message = "The status can not be blank")
	private String status;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "world_id")
	@JsonBackReference
	private World originWorld;
	
	private String description;

	public CosmereCharacter() {
		super();
	}

	public CosmereCharacter(Long id, @NotBlank(message = "The name can not be blank") String name, Date birthDate,
			Date deathDate, @NotBlank(message = "The status can not be blank") String status, World originWorld,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.status = status;
		this.originWorld = originWorld;
		this.description = description;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public World getOriginWorld() {
		return originWorld;
	}

	public void setOriginWorld(World originWorld) {
		this.originWorld = originWorld;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
