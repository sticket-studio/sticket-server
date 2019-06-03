package com.ec.sticket.config;

import com.ec.sticket.util.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Arrays;

/**
 * OAuth 인가 과정을 처리하는 설정
 * 토큰 발급 등
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String GRANT_TYPE_PASSWORD = "password";
    static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
    static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    static final String GRANT_TYPE_IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 30 * 60;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    @Value("${oauth.client-id}")
    private String CLIENT_ID;
    @Value("${oauth.client-secret}")
    private String CLIENT_SECRET;
    @Value("${jwt.jk-path}")
    private String JK_PATH;
    @Value("${jwt.jk-password}")
    private String JK_PASSWORD;
    @Value("${jwt.key-alias}")
    private String KEY_ALIAS;
    @Value("${jwt.key-password}")
    private String KEY_PASSWORD;

    // TODO : DB를 사용하는 TokenStore로 바꿔야함
    private final TokenStore tokenStore;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final CustomTokenEnhancer customTokenEnhancer;

    public AuthorizationServerConfig(TokenStore tokenStore, AuthenticationManager authenticationManager
            , PasswordEncoder passwordEncoder, CustomTokenEnhancer customTokenEnhancer) {
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.customTokenEnhancer = customTokenEnhancer;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, GRANT_TYPE_AUTHORIZATION_CODE
                        , GRANT_TYPE_REFRESH_TOKEN, GRANT_TYPE_IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(customTokenEnhancer, jwtAccessTokenConverter()));

        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource(JK_PATH), JK_PASSWORD.toCharArray())
                .getKeyPair(KEY_ALIAS, KEY_PASSWORD.toCharArray());
        converter.setKeyPair(keyPair);
        return converter;
    }
}
