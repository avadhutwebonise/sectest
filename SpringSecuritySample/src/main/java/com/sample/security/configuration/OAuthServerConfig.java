package com.sample.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class OAuthServerConfig {

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
        
        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
             endpoints.authenticationManager(authenticationManager);
        }
        
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
             // @formatter:off
             clients.inMemory()
             .withClient("client-with-registered-redirect")
             .authorizedGrantTypes("authorization_code")
             .authorities("ROLE_CLIENT")
             .scopes("read", "trust")
             .resourceIds("user_resource")
             .redirectUris("http://anywhere?key=value")
             .secret("secret123")
             .and()
             .withClient("my-client-with-secret")
             .authorizedGrantTypes("client_credentials", "password")
             .authorities("ROLE_CLIENT")
             .scopes("read")
             .resourceIds("user_resource")
             .secret("secret");
             // @formatter:on
        } 
    }
    
}
