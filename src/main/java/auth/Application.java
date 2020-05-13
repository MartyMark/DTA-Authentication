package auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * https://blog.ordix.de/technologien/oauth-2-0-und-java-spring-rest-schnittstellen-absichern-mit-spring-oauth-2-0-json-web-token
 */
@SpringBootApplication
@EnableAuthorizationServer
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
