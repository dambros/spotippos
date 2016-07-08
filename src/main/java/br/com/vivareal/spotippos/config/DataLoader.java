package br.com.vivareal.spotippos.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import br.com.vivareal.spotippos.dto.JsonProvince;
import br.com.vivareal.spotippos.mapping.JsonProvinceMapping;
import br.com.vivareal.spotippos.model.Province;
import br.com.vivareal.spotippos.repository.ProvinceRepository;

@Configuration
public class DataLoader {

  private static final String PROVINCES_JSON = "provinces.json";

  private static final Logger LOGGER = Logger.getLogger(DataLoader.class);

  @Autowired
  private ProvinceRepository provinceRepository;

  @PostConstruct
  private void loadProvinces() {
    ObjectMapper mapper = new ObjectMapper();
    List<Province> provinces = new ArrayList<>();
    ModelMapper jsonProvinceMapping = new ModelMapper();
    jsonProvinceMapping.addMappings(new JsonProvinceMapping());

    try {
      Map<String, JsonProvince> temp = mapper.readValue(getClass().getClassLoader().getResource(PROVINCES_JSON),
              new TypeReference<Map<String, JsonProvince>>() {});

      for (Map.Entry<String, JsonProvince> tempProvince : temp.entrySet()) {
        Province province = jsonProvinceMapping.map(tempProvince.getValue(), Province.class);
        province.setName(tempProvince.getKey());
        provinces.add(province);
      }

      provinceRepository.save(provinces);

    } catch (IOException e) {
      LOGGER.error("Exception while loading provinces", e);
    }

  }
}
