package br.com.vivareal.spotippos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "provinces")
public class Province {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "province_id")
  private Long id;

  @Column(name = "province_name", nullable = false, length = 50)
  private String name;

  @Column(name = "province_upper_left_x", nullable = false, length = 4)
  private Integer upperLeftX;

  @Column(name = "province_upper_left_y", nullable = false, length = 4)
  private Integer upperLeftY;

  @Column(name = "province_bottom_right_x", nullable = false, length = 4)
  private Integer bottomRightX;

  @Column(name = "province_bottom_right_y", nullable = false, length = 4)
  private Integer bottomRightY;

  @JsonIgnore
  @ManyToMany(mappedBy = "provinces", fetch = FetchType.LAZY)
  @LazyCollection(LazyCollectionOption.EXTRA)
  private List<Property> properties;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getUpperLeftX() {
    return upperLeftX;
  }

  public void setUpperLeftX(Integer upperLeftX) {
    this.upperLeftX = upperLeftX;
  }

  public Integer getUpperLeftY() {
    return upperLeftY;
  }

  public void setUpperLeftY(Integer upperLeftY) {
    this.upperLeftY = upperLeftY;
  }

  public Integer getBottomRightX() {
    return bottomRightX;
  }

  public void setBottomRightX(Integer bottomRightX) {
    this.bottomRightX = bottomRightX;
  }

  public Integer getBottomRightY() {
    return bottomRightY;
  }

  public void setBottomRightY(Integer bottomRightY) {
    this.bottomRightY = bottomRightY;
  }

  public List<Property> getProperties() {
    return properties;
  }

  public void setProperties(List<Property> properties) {
    this.properties = properties;
  }
}
