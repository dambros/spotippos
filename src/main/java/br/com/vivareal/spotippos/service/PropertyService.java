package br.com.vivareal.spotippos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.vivareal.spotippos.dto.PropertyDTO;
import br.com.vivareal.spotippos.dto.PropertySearchDTO;
import br.com.vivareal.spotippos.dto.PropertySearchResponseDTO;
import br.com.vivareal.spotippos.mapping.PropertyDTOMapping;
import br.com.vivareal.spotippos.model.Property;
import br.com.vivareal.spotippos.model.Province;
import br.com.vivareal.spotippos.repository.PropertyRepository;
import br.com.vivareal.spotippos.repository.ProvinceRepository;

@Service
public class PropertyService {

  private static ModelMapper propertyDTOMapper;

  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private ProvinceRepository provinceRepository;

  static {
    propertyDTOMapper = new ModelMapper();
    propertyDTOMapper.addMappings(new PropertyDTOMapping());
  }

  public PropertyDTO create(PropertyDTO dto) {
    Property property = propertyDTOMapper.map(dto, Property.class);
    List<Province> provinces = provinceRepository.findByXAndY(property.getX(), property.getY());
    property.setProvinces(provinces);
    property = propertyRepository.save(property);
    return propertyDTOMapper.map(property, PropertyDTO.class);
  }

  public PropertyDTO getById(Long id) {
    Property property = propertyRepository.findById(id);
    if (property != null) {
      return propertyDTOMapper.map(property, PropertyDTO.class);
    }
    return null;
  }

  public PropertySearchResponseDTO findAllBetweenCoordinates(PropertySearchDTO dto) {

    //since all values on the dto have already been validated, I can be sure they all are correct numeric strings here
    List<Property> properties = propertyRepository.findAllBetweenCoordinates(
            Integer.valueOf(dto.getAx()),
            Integer.valueOf(dto.getAy()),
            Integer.valueOf(dto.getBx()),
            Integer.valueOf(dto.getBy()));

    List<PropertyDTO> dtos = new ArrayList<>(properties.size());

    if (!properties.isEmpty()) {
      for (Property p : properties) {
        dtos.add(propertyDTOMapper.map(p, PropertyDTO.class));
      }
    }

    return new PropertySearchResponseDTO(dtos.size(), dtos);
  }
}
