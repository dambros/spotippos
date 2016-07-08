package br.com.vivareal.spotippos.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {

  @Autowired
  private MessageSource messageSource;

  public String getMessage(String key) {
    String message = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    if (StringUtils.isNotEmpty(message)) {
      return message;
    }
    return key;
  }
}
