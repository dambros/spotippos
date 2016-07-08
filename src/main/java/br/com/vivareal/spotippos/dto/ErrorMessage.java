package br.com.vivareal.spotippos.dto;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

  private Map<String, Object> errors;

  public ErrorMessage(Map<String, Object> errors) {
    this.errors = errors;
  }

  public ErrorMessage(String key, Object value) {
    addErrors(key, value);
  }

  public ErrorMessage() {
  }

  public void addErrors(String key, Object value) {
    if (errors == null) {
      errors = new HashMap<>();
    }
    key = key != null ? key : "value";
    errors.put(key, value);
  }

  public Map<String, Object> getErrors() {
    return errors;
  }
}
