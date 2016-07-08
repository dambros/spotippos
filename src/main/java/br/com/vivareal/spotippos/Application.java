package br.com.vivareal.spotippos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class Application {

  public static void main(String[] args)  {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasenames("ValidationMessages");
    source.setUseCodeAsDefaultMessage(true);
    return source;
  }
}