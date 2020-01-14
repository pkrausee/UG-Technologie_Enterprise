package pl.edu.ug.tent.springweb.Configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.ug.tent.springweb.Models.CalculatorHistory;
import pl.edu.ug.tent.springweb.Models.CollectionBean;
import pl.edu.ug.tent.springweb.Servlet.CalculatorServlet;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration {

  @Bean
  public ServletRegistrationBean<CalculatorServlet> servletRegistrationCalculatorBean(){
    return new ServletRegistrationBean<>(new CalculatorServlet(), "/Calculator");
  }

  @Bean
  public CollectionBean getCollectionBean() {
    return new CollectionBean();
  }

  @Bean
  public List<CalculatorHistory> calculatorHistory(){
    return new ArrayList<>();
  }

}
