package com.mires.bdbt.parkrozrywki;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
		scanBasePackages = {
				"com.mires.bdbt.parkrozrywki"
		}
)
public class ParkRozrywkiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkRozrywkiApplication.class, args);
	}

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
		return factory -> factory.addAdditionalTomcatConnectors(createHttpConnector());
	}

	private Connector createHttpConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setPort(8080); // HTTP port
		connector.setScheme("http");
		connector.setSecure(false);
		connector.setRedirectPort(8443); // Redirect to HTTPS port
		return connector;
	}

}
