package br.com.vivareal.spotippos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import br.com.vivareal.spotippos.model.Province;

public interface ProvinceRepository extends CrudRepository<Province, Long> {

    Province findById(Long id);

    @Query("SELECT p FROM Province p WHERE ?1 >= p.upperLeftX AND ?1 <= p.bottomRightX AND ?2 <= p.upperLeftY AND ?2 >= p.bottomRightY")
    List<Province> findByXAndY(int x, int y);
}