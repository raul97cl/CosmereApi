package com.cosmere.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosmere.entity.World;

@Repository
public interface WorldRepository extends CrudRepository<World, Long> {

	public World save(World world);
	
	public Optional<World> findById(Long worldId);
	
	@Query(value = "SELECT * FROM  world WHERE REPLACE(name, ' ', '') LIKE ('%' || REPLACE(:name, ' ', '') || '%')", nativeQuery = true)
	public Set<World> findbyNameLike(@Param("name") String name);
	
	public void deleteById(Long worldId);
}
