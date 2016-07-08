package br.com.vivareal.spotippos.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PropertySearchDTO {

  @NotEmpty(message = "{error.property.required}")
  @Min(value = 0, message = "{error.property.coordinates.length}")
  @Max(value = 1400, message = "{error.property.coordinates.length}")
  private String ax;

  @NotEmpty(message = "{error.property.required}")
  @Min(value = 0, message = "{error.property.coordinates.length}")
  @Max(value = 1000, message = "{error.property.coordinates.length}")
  private String ay;

  @NotEmpty(message = "{error.property.required}")
  @Min(value = 0, message = "{error.property.coordinates.length}")
  @Max(value = 1400, message = "{error.property.coordinates.length}")
  private String bx;

  @NotEmpty(message = "{error.property.required}")
  @Min(value = 0, message = "{error.property.coordinates.length}")
  @Max(value = 1000, message = "{error.property.coordinates.length}")
  private String by;

  public String getAx() {
    return ax;
  }

  public void setAx(String ax) {
    this.ax = ax;
  }

  public String getAy() {
    return ay;
  }

  public void setAy(String ay) {
    this.ay = ay;
  }

  public String getBx() {
    return bx;
  }

  public void setBx(String bx) {
    this.bx = bx;
  }

  public String getBy() {
    return by;
  }

  public void setBy(String by) {
    this.by = by;
  }
}
