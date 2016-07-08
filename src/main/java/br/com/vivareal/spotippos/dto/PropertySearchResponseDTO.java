package br.com.vivareal.spotippos.dto;

import java.util.List;

public class PropertySearchResponseDTO {

  private Integer foundProperties;
  private List<PropertyDTO> properties;

  public PropertySearchResponseDTO(Integer foundProperties, List<PropertyDTO> properties) {
    this.foundProperties = foundProperties;
    this.properties = properties;
  }

  public Integer getFoundProperties() {
    return foundProperties;
  }

  public void setFoundProperties(Integer foundProperties) {
    this.foundProperties = foundProperties;
  }

  public List<PropertyDTO> getProperties() {
    return properties;
  }

  public void setProperties(List<PropertyDTO> properties) {
    this.properties = properties;
  }
}
