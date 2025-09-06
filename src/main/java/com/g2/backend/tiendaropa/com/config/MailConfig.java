//// src/main/java/com/g2/backend/tiendaropa/com/config/MailConfig.java (o donde quieras tu configuración)
//
//package com.g2.backend.tiendaropa.com.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration; // Importa esta anotación
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration // Indica a Spring que esta clase contiene definiciones de beans
//public class MailConfig {
//
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        // Spring Boot se encargará de configurar las propiedades de spring.mail.*
//        // No necesitas configurarlas manualmente aquí a menos que quieras sobrescribir
//        return mailSender;
//    }
//}