package br.com.vivareal.spotippos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "properties")
public class Property {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "property_id")
  private Long id;

  @Column(name = "property_x", nullable = false, length = 4)
  private Integer x;

  @Column(name = "property_y", nullable = false, length = 4)
  private Integer y;

  @Column(name = "property_beds", nullable = false, length = 1)
  private Integer beds;

  @Column(name = "property_baths", nullable = false, length = 1)
  private Integer baths;

  @Column(name = "property_square_meters", nullable = false, length = 3)
  private Double squareMeters;

  @Column(name = "property_price", nullable = false, precision = 2)
  private Double price;

  @Column(name = "property_title", nullable = false)
  private String title;

  @Column(name = "property_description", nullable = false)
  private String description;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "property_provinces", joinColumns = {
          @JoinColumn(name = "property_id", nullable = false)}, inverseJoinColumns = {
          @JoinColumn(name = "province_id", nullable = false)
  })
  private List<Province> provinces;

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

  public List<Province> getProvinces() {
    return provinces;
  }

  public void setProvinces(List<Province> provinces) {
    this.provinces = provinces;
  }

  public List<String> getProvincesAsString() {
    if (this.provinces != null && !this.provinces.isEmpty()) {
      List<String> temp = new ArrayList<>();

      for (Province province : this.provinces) {
        temp.add(province.getName());
      }
      return temp;
    }
    return null;
  }
}
