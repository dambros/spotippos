package br.com.vivareal.spotippos.mapping;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;

import br.com.vivareal.spotippos.dto.JsonProvince;
import br.com.vivareal.spotippos.model.Province;

public class JsonProvinceMapping extends PropertyMap<JsonProvince, Province> {

  @Override
  protected void configure() {
    Condition notNull = mappingContext -> mappingContext.getSource() != null;
    when(notNull).map().setBottomRightX(source.getBottomRightX());
    when(notNull).map().setBottomRightY(source.getBottomRightY());
    when(notNull).map().setUpperLeftX(source.getUpperLeftX());
    when(notNull).map().setUpperLeftY(source.getUpperLeftY());
  }
}
