package org.martinez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisDemoApplication {

  public static void main(String[] args) {
    System.out.println("Hello world!");
    SpringApplication.run(RedisDemoApplication.class, args);
  }
}