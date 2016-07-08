package br.com.vivareal.spotippos.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PropertyDTO {

  private Long id;

  @NotNull(message = "{error.property.required}")
  @Min(value = 0, message = "{error.property.coordinates.length}")
  @Max(value = 1400, message = "{error.property.coordinates.length}")
  private Integer x;

  @NotNull(message = "{error.property.required}")
  @Min(value = 0, message = "{error.property.coordinates.length}")
  @Max(value = 1000, message = "{error.property.coordinates.length}")
  private Integer y;

  @NotNull(message = "{error.property.required}")
  @Min(value = 1, message = "{error.property.beds.quantity}")
  @Max(value = 5, message = "{error.property.beds.quantity}")
  private Integer beds;

  @NotNull(message = "{error.property.required}")
  @Min(value = 1, message = "{error.property.baths.quantity}")
  @Max(value = 4, message = "{error.property.baths.quantity}")
  private Integer baths;

  @NotNull(message = "{error.property.required}")
  @Min(value = 20, message = "{error.property.squareMeters.size}")
  @Max(value = 240, message = "{error.property.squareMeters.size}")
  private Double squareMeters;

  @NotNull(message = "{error.property.required}")
  private Double price;

  @NotEmpty(message = "{error.property.required}")
  private String title;

  @NotEmpty(message = "{error.property.required}")
  private String description;

  private List<String> provinces;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getBeds() {
    return beds;
  }

  public void setBeds(Integer beds) {
    this.beds = beds;
  }

  public Integer getBaths() {
    return baths;
  }

  public void setBaths(Integer baths) {
    this.baths = baths;
  }

  public Double getSquareMeters() {
    return squareMeters;
  }

  public void setSquareMeters(Double squareMeters) {
    this.squareMeters = squareMeters;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getProvinces() {
    return provinces;
  }

  public void setProvinces(List<String> provinces) {
    this.provinces = provinces;
  }
}
