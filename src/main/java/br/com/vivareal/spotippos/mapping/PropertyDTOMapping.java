package br.com.vivareal.spotippos.mapping;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;

import br.com.vivareal.spotippos.dto.PropertyDTO;
import br.com.vivareal.spotippos.model.Property;

public class PropertyDTOMapping extends PropertyMap<Property, PropertyDTO> {

  @Override
  protected void configure() {
    Condition notNull = mappingContext -> mappingContext.getSource() != null;
    when(notNull).map().setProvinces(source.getProvincesAsString());
  }
}
