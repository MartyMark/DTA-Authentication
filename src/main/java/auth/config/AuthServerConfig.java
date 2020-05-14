package auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Diese Klasse dient zum generieren des Tokens
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${security.oauth2.client.clientId}")
	private String clientId;
	@Value("${security.oauth2.client.clientSecret}")
	private String clientSecret;

	/**
	 * In dieser Methode werden die Zugriffsrechte für die zwei Endpunkte ​token sowie ​check_token definiert. 
	 * Grundsätzlich sollte hier der Endpunkt ​token für jeden Nutzer zur Verfügung stehen, da dieser für die Anmeldung am System benötigt wird. 
	 * Der Endpunkt ​check_token muss nur für die Nutzer zur Verfügung stehen, die bereits am System angemeldet sind. 
	 * Daher wird hierbei ​isAuthenticated() als Berechtigungsmethode verwendet.
	 */
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	/**
	 * Hierdurch werden die notwendigen Client-Informationen bereitgestellt, d. h., dass die OAuth2.0-Protokolle für verschiedene Clients konfiguriert werden. 
	 * In diesem Beispiel wird nur ein Client mit der ID und dem Secret verwendet. 
	 * Diesem Client wird der Grant_Type ​password mitgegeben, was dem gleichnamigen Flow von OAuth2.0 entspricht.
	 */
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).secret("{noop}" + clientSecret)
				.authorizedGrantTypes("password")
				.scopes("user_info").autoApprove(true);
	}
	
	/**
	 * Diese Methode dient zum Koppeln der standard­mäßig von Spring zur Verfügung gestellten Endpunkte für OAuth2.0 mit den Implementierungen unserer Applikation. 
	 * Da es sich in diesem Beispiel um ein JSON Web Token als Access-Token handelt, wird hier der dazugehörige ​JwtTokenStore mit dem Endpunkt verknüpft. 
	 * Des Weiteren wird hier der ​AuthentcationManager mitgegeben, der zuvor in der Security-Konfiguration mit Nutzerinformationen versorgt wurde.
	 */
	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore());
	}

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
}

