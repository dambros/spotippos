package br.com.vivareal.spotippos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import br.com.vivareal.spotippos.model.Property;
import br.com.vivareal.spotippos.model.Province;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    Property findById(Long id);


    @Query("SELECT p FROM Property p WHERE p.x >= ?1 AND p.x <= ?3 AND p.y <= ?2 AND p.y >= ?4")
    List<Property> findAllBetweenCoordinates(int ax, int ay, int bx, int by);
}