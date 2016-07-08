package br.com.vivareal.spotippos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import br.com.vivareal.spotippos.dto.ErrorMessage;
import br.com.vivareal.spotippos.dto.PropertyDTO;
import br.com.vivareal.spotippos.dto.PropertySearchDTO;
import br.com.vivareal.spotippos.dto.PropertySearchResponseDTO;
import br.com.vivareal.spotippos.service.PropertyService;
import br.com.vivareal.spotippos.utils.MessageHelper;

@RestController
@RequestMapping(value = "properties", produces = "application/json;charset=UTF-8")
public class PropertyController {

  @Autowired
  private PropertyService propertyService;

  @Autowired
  private MessageHelper messageHelper;

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity create(@RequestBody @Valid PropertyDTO dto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return getErrorMessage(bindingResult);
    }

    return new ResponseEntity(propertyService.create(dto), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getById(@PathVariable Long id) {

    PropertyDTO dto = propertyService.getById(id);

    if (dto == null) {
      return new ResponseEntity(
              new ErrorMessage("id", messageHelper.getMessage("error.not.found")),
              HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity(dto, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity findAllBetweenCoordinates(@Valid @ModelAttribute PropertySearchDTO dto, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return getErrorMessage(bindingResult);
    }
    PropertySearchResponseDTO responseDTO = propertyService.findAllBetweenCoordinates(dto);
    return new ResponseEntity(responseDTO, HttpStatus.OK);
  }


  private ResponseEntity getErrorMessage(BindingResult bindingResult) {
    ErrorMessage errorMessage = new ErrorMessage();

    for (FieldError error : bindingResult.getFieldErrors()) {
      errorMessage.addErrors(error.getField(), error.getDefaultMessage());
    }

    return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
  }
}