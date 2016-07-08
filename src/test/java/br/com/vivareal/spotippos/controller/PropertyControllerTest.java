package br.com.vivareal.spotippos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.MatcherAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.vivareal.spotippos.BaseTest;
import br.com.vivareal.spotippos.dto.PropertyDTO;
import br.com.vivareal.spotippos.utils.MessageHelper;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PropertyControllerTest extends BaseTest {

  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MessageHelper messageHelper;

  @Before
  public void init() {
    super.init();
  }

  @Test
  public void testPropertyCreation_FailureExpected_NullValues() throws Exception {

    PropertyDTO dto = new PropertyDTO();
    String json = mapper.writeValueAsString(dto);

    JSONObject jObject = new JSONObject(mockMvc.perform(post("/properties")
            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    String expectedMessage = messageHelper.getMessage("error.property.required");

    MatcherAssert.assertThat(jObject.length(), equalTo(8));
    MatcherAssert.assertThat(jObject.getString("squareMeters"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("baths"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("price"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("x"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("y"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("description"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("beds"), equalTo(expectedMessage));
    MatcherAssert.assertThat(jObject.getString("title"), equalTo(expectedMessage));
  }

  @Test
  public void testPropertyCreation_FailureExpected_InvalidValues_InitialRange() throws Exception {

    PropertyDTO dto = getDTO();

    //all invalid values, should generate 5 error messages
    dto.setX(-1);
    dto.setY(-1);
    dto.setBeds(0);
    dto.setBaths(0);
    dto.setSquareMeters(19d);


    String json = mapper.writeValueAsString(dto);

    JSONObject jObject = new JSONObject(mockMvc.perform(post("/properties")
            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    MatcherAssert.assertThat(jObject.length(), equalTo(5));
    MatcherAssert.assertThat(jObject.getString("squareMeters"), equalTo(messageHelper.getMessage("error.property.squareMeters.size")));
    MatcherAssert.assertThat(jObject.getString("baths"), equalTo(messageHelper.getMessage("error.property.baths.quantity")));
    MatcherAssert.assertThat(jObject.getString("beds"), equalTo(messageHelper.getMessage("error.property.beds.quantity")));
    MatcherAssert.assertThat(jObject.getString("x"), equalTo(messageHelper.getMessage("error.property.coordinates.length")));
    MatcherAssert.assertThat(jObject.getString("y"), equalTo(messageHelper.getMessage("error.property.coordinates.length")));

    //still invalid values, should generate 5 error messages
    dto.setX(1401);
    dto.setY(1001);
    dto.setBeds(6);
    dto.setBaths(5);
    dto.setSquareMeters(241d);

    json = mapper.writeValueAsString(dto);
    jObject = new JSONObject(mockMvc.perform(post("/properties")
            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    MatcherAssert.assertThat(jObject.length(), equalTo(5));
    MatcherAssert.assertThat(jObject.getString("squareMeters"), equalTo(messageHelper.getMessage("error.property.squareMeters.size")));
    MatcherAssert.assertThat(jObject.getString("baths"), equalTo(messageHelper.getMessage("error.property.baths.quantity")));
    MatcherAssert.assertThat(jObject.getString("beds"), equalTo(messageHelper.getMessage("error.property.beds.quantity")));
    MatcherAssert.assertThat(jObject.getString("x"), equalTo(messageHelper.getMessage("error.property.coordinates.length")));
    MatcherAssert.assertThat(jObject.getString("y"), equalTo(messageHelper.getMessage("error.property.coordinates.length")));
  }

  @Test
  public void testPropertyCreation_FailureExpected_InvalidValues_FinalRange() throws Exception {

    PropertyDTO dto = getDTO();

    //all invalid values, should generate 5 error messages
    dto.setX(1401);
    dto.setY(1001);
    dto.setBeds(6);
    dto.setBaths(5);
    dto.setSquareMeters(241d);

    String json = mapper.writeValueAsString(dto);

    JSONObject jObject = new JSONObject(mockMvc.perform(post("/properties")
            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    MatcherAssert.assertThat(jObject.length(), equalTo(5));
    MatcherAssert.assertThat(jObject.getString("squareMeters"), equalTo(messageHelper.getMessage("error.property.squareMeters.size")));
    MatcherAssert.assertThat(jObject.getString("baths"), equalTo(messageHelper.getMessage("error.property.baths.quantity")));
    MatcherAssert.assertThat(jObject.getString("beds"), equalTo(messageHelper.getMessage("error.property.beds.quantity")));
    MatcherAssert.assertThat(jObject.getString("x"), equalTo(messageHelper.getMessage("error.property.coordinates.length")));
    MatcherAssert.assertThat(jObject.getString("y"), equalTo(messageHelper.getMessage("error.property.coordinates.length")));
  }

  @Test
  public void testPropertyCreation_SuccessExpected() throws Exception {

    PropertyDTO dto = persistDTO(getDTO());

    MatcherAssert.assertThat(dto.getId(), notNullValue());
    MatcherAssert.assertThat(dto.getProvinces().size(), equalTo(2));
    MatcherAssert.assertThat(dto.getProvinces().get(0), equalTo("Gode"));
    MatcherAssert.assertThat(dto.getProvinces().get(1), equalTo("Ruja"));
  }

  @Test
  public void testGetPropertyById_FailureExpected_NotFound() throws Exception {

    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties/{id}", 999l))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    MatcherAssert.assertThat(jObject.length(), equalTo(1));
    MatcherAssert.assertThat(jObject.getString("id"), equalTo(messageHelper.getMessage("error.not.found")));
  }

  @Test
  public void testGetPropertyById_SuccessExpected() throws Exception {

    //creating new Property for recovering it later
    PropertyDTO dto = persistDTO(getDTO());

    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties/{id}", dto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString());

    MatcherAssert.assertThat(jObject.getLong("id"), equalTo(dto.getId()));
    MatcherAssert.assertThat(jObject.getString("description"), equalTo(dto.getDescription()));
    MatcherAssert.assertThat(jObject.getString("title"), equalTo(dto.getTitle()));
    MatcherAssert.assertThat(jObject.getInt("baths"), equalTo(dto.getBaths()));
    MatcherAssert.assertThat(jObject.getInt("beds"), equalTo(dto.getBeds()));
    MatcherAssert.assertThat(jObject.getDouble("price"), equalTo(dto.getPrice()));
    MatcherAssert.assertThat(jObject.getDouble("squareMeters"), equalTo(dto.getSquareMeters()));
    MatcherAssert.assertThat(jObject.getInt("x"), equalTo(dto.getX()));
    MatcherAssert.assertThat(jObject.getInt("y"), equalTo(dto.getY()));
    MatcherAssert.assertThat(jObject.getJSONArray("provinces").length(), equalTo(dto.getProvinces().size()));
    MatcherAssert.assertThat(jObject.getJSONArray("provinces").get(0), equalTo(dto.getProvinces().get(0)));
    MatcherAssert.assertThat(jObject.getJSONArray("provinces").get(1), equalTo(dto.getProvinces().get(1)));
  }

  @Test
  public void testFindAllBetweenCoordinates_FailureExpected_MissingParams() throws Exception {

    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    String errorMessage = "error.property.required";
    MatcherAssert.assertThat(jObject.length(), equalTo(4));
    MatcherAssert.assertThat(jObject.getString("ax"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("ay"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("bx"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("by"), equalTo(messageHelper.getMessage(errorMessage)));
  }

  @Test
  public void testFindAllBetweenCoordinates_FailureExpected_InvalidParams_InitialRange() throws Exception {

    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties")
            .param("ax", "-1")
            .param("ay", "-1")
            .param("bx", "-1")
            .param("by", "-1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    String errorMessage = "error.property.coordinates.length";
    MatcherAssert.assertThat(jObject.length(), equalTo(4));
    MatcherAssert.assertThat(jObject.getString("ax"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("ay"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("bx"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("by"), equalTo(messageHelper.getMessage(errorMessage)));
  }

  @Test
  public void testFindAllBetweenCoordinates_FailureExpected_InvalidParams_FinalRange() throws Exception {

    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties")
            .param("ax", "1401")
            .param("ay", "1001")
            .param("bx", "1401")
            .param("by", "1001"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    String errorMessage = "error.property.coordinates.length";
    MatcherAssert.assertThat(jObject.length(), equalTo(4));
    MatcherAssert.assertThat(jObject.getString("ax"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("ay"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("bx"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("by"), equalTo(messageHelper.getMessage(errorMessage)));
  }

  @Test
  public void testFindAllBetweenCoordinates_FailureExpected_InvalidParams() throws Exception {

    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties")
            .param("ax", "asdada")
            .param("ay", "adsd")
            .param("bx", "14dasda01")
            .param("by", "asdaa1212ad"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString()).getJSONObject("errors");

    String errorMessage = "error.property.coordinates.length";
    MatcherAssert.assertThat(jObject.length(), equalTo(4));
    MatcherAssert.assertThat(jObject.getString("ax"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("ay"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("bx"), equalTo(messageHelper.getMessage(errorMessage)));
    MatcherAssert.assertThat(jObject.getString("by"), equalTo(messageHelper.getMessage(errorMessage)));
  }

  @Test
  public void testFindAllBetweenCoordinates_SuccessExpected() throws Exception {

    //creating new Property for recovering it later
    PropertyDTO dto1 = persistDTO(getDTO()); // x = 500, y = 900

    PropertyDTO dto2 = getDTO();
    dto2.setTitle("title2");
    dto2.setX(300);
    dto2.setY(400);
    dto2 = persistDTO(dto2); // x = 300, y = 400

    PropertyDTO dto3 = getDTO();
    dto3.setTitle("title3");
    dto3.setX(1300);
    dto3.setY(980);
    dto3 = persistDTO(dto3); // x = 1300, y = 1000

    PropertyDTO dto4 = getDTO();
    dto4.setTitle("title4");
    dto4.setX(700);
    dto4.setY(500);
    dto4 = persistDTO(dto4); // x = 700, y = 500

    //find all 4 values because ax <= lowest X (300), ay >= highest y (980), bx >= highest x (1300) and by <= lowest y (400),
    //which should create an area where all 4 properties and contained
    JSONObject jObject = new JSONObject(mockMvc.perform(get("/properties")
            .param("ax", "295")
            .param("ay", "985")
            .param("bx", "1305")
            .param("by", "385"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString());

    JSONArray properties = jObject.getJSONArray("properties");
    MatcherAssert.assertThat(jObject.getInt("foundProperties"), equalTo(4));
    MatcherAssert.assertThat(properties.getJSONObject(0).getLong("id"), equalTo(dto1.getId()));
    MatcherAssert.assertThat(properties.getJSONObject(1).getLong("id"), equalTo(dto2.getId()));
    MatcherAssert.assertThat(properties.getJSONObject(2).getLong("id"), equalTo(dto3.getId()));
    MatcherAssert.assertThat(properties.getJSONObject(3).getLong("id"), equalTo(dto4.getId()));

    //find only 2 properties
    jObject = new JSONObject(mockMvc.perform(get("/properties")
            .param("ax", "295")
            .param("ay", "900")
            .param("bx", "505")
            .param("by", "400"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString());

    properties = jObject.getJSONArray("properties");
    MatcherAssert.assertThat(jObject.getInt("foundProperties"), equalTo(2));
    MatcherAssert.assertThat(properties.getJSONObject(0).getLong("id"), equalTo(dto1.getId()));
    MatcherAssert.assertThat(properties.getJSONObject(1).getLong("id"), equalTo(dto2.getId()));

    //doesn't find any properties
    jObject = new JSONObject(mockMvc.perform(get("/properties")
            .param("ax", "1301")
            .param("ay", "900")
            .param("bx", "1400")
            .param("by", "400"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString());

    MatcherAssert.assertThat(jObject.getInt("foundProperties"), equalTo(0));
  }

  private PropertyDTO getDTO() {
    PropertyDTO dto = new PropertyDTO();
    dto.setPrice(100d);
    dto.setTitle("title");
    dto.setDescription("description");
    dto.setX(500);
    dto.setY(900);
    dto.setBeds(4);
    dto.setBaths(1);
    dto.setSquareMeters(220d);
    return dto;
  }

  private PropertyDTO persistDTO(PropertyDTO dto) throws Exception {
   String response = mockMvc.perform(post("/properties")
            .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andReturn().getResponse().getContentAsString();

    return mapper.readValue(response, PropertyDTO.class);
  }
}
