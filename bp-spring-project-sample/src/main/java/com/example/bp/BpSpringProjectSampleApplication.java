package com.example.bp;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
public class BpSpringProjectSampleApplication {

  
  public static void main(String[] args) {
    SpringApplication.run(BpSpringProjectSampleApplication.class, args);
  }
  
  

  /**
   * {@link LocaleResolver} based on URL parameter locale
   *
   * @return Instance of {@link LocaleResolver}.
   */
  @Bean
  public LocaleResolver localeResolver() {
    return new LocaleResolver() {

      @Override
      public Locale resolveLocale(HttpServletRequest request) {
        String newLocale = request.getParameter("locale");
        if (newLocale != null) {
          return Locale.forLanguageTag(newLocale);
        }
        return Locale.ENGLISH;
      }

      @Override
      public void setLocale(HttpServletRequest request, HttpServletResponse response,
          Locale locale) {
        // TODO: do nothing
      }

    };
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
