package co.nilin.security.oauth2jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private String clientid="tutorialspoint";
    private String clientSecret="$2a$10$a2hzek3AV6QKRrRDlek5SuqNRi3N35dCtKmdKzD2QoLzMU75iqlHO";
    private String privateKey="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAiGpmTq2kEVA4ddpy31iMHA8q89K3WQrus4khmOtYhHzo3quD\n" +
            "nv+jRaNlcFlI1a6nVRC8t7qbXDHYHdmEccNWuksIEeA4K43cGDNCsa5sRTwu56wg\n" +
            "CIfbfqYArwScjtjg4Sb+a21mxyiBkesNp+3ejHJlIx3mp8bPIeE8O6Hh9WU4ojeF\n" +
            "XLel8zcJEnAeeRG/f8/y8b+xRaRP6YwCW08vXAd5RaQVUZSdGX63nsAH56WwQQEm\n" +
            "ID2Tc2JlJal/ahEoOn3xs/GcanJ3LKDYoMh5jcM7jqXCLQzDpv4V7TOEvPnTNvDG\n" +
            "TdzUDcOny3tal3oL4ruEPU5LUruMTEdNlL/1MQIDAQABAoIBAD5446Nyd+mJ4GFw\n" +
            "ihzokzmiZJxGyrqXfBtyWJWMafw6q/aVe3CMR/xzSNg7u1RIaM9XG+XUKqP9G6kH\n" +
            "3VSwDX7BL8/n7DFeBQZ8KzXt2B2Y4y4rsHvXo9iLM1LzfLMadVoHHoqaDnB0h2oI\n" +
            "yDc4gyu0k8aSQfhh3qf4Wzo/95gGeM/Btk6qtC+OLCAx+J5MRNQf3sl7145J4BS0\n" +
            "k6zUdOFGbliiu3Ls1MWXz+iw1R9ATiSxaeCvMdup/qTW03FxSegwUQcmV9QCt3CA\n" +
            "11VhOcq9lvdcWSNnF2mCktU9N5Guxhh2qzjCo09lwNhBuCfDGSzkNkqcdpYTDwxE\n" +
            "JIY+M5UCgYEAu8gPnElvOba9XWu5kKYhCUbV9I7BLPd+RbsaI/qk+t8lk/ck+o7c\n" +
            "Xitiqp7qblb5EFrvh47c0TbsulWEClHSWGmp+l69ebSCBwUL3zGawoCYxL6hcGV7\n" +
            "Kfc7s9UAKd0bomS6S2RBkQuDoYmmp0Ho2hpsxpk19gnLjYGOAtXe4DcCgYEAufk9\n" +
            "8SQg+6a+M0lQED+VTqBzvxowOiofz1so+st9KZkHDdbBvMInhHxWFzasciqAr2FI\n" +
            "gLRs9cPRVSa+ZDH5GJhVlKZEIysUolDHFl1scvWp2SSEbisXMW6k6Q1TLBK+igN3\n" +
            "Bs2ugQcI4NZpjmWVasttFnHOcHBOBmOXH8DCEdcCgYBFzz5eb751ykXj8SRvbhNi\n" +
            "C3+HB3sxaGD9M22oaoni6gpiU8LfkzTBnYd0vteQZaz6M5RQ5P+al7yyGRCZdYmp\n" +
            "bL7QYWA3Wq/sRCgBtcM/A+fHXMdPrap4E32SgWEqLxUS7jeDx6XIcmthJndaPtp+\n" +
            "GszNmC/DZCjUVpGg5DmTSQKBgGVu5PL+UqMPKhRM+0cxdt0gtKDZxs4ipaDyRqKr\n" +
            "lEO4/Fj29kpe1zDyo/4bSukl93QwqlvQjNFrEWTRMezyOLvKcnsWSHmqCKITTtMK\n" +
            "95ioFxChUz3HcOn8QCQAqYmTW6tpAmWKj5A+ZwBC0eXh0CIkfAZO4lr/Vf80GHww\n" +
            "JWmXAoGBALkgrIubvGjkQWfmuGPBRPplTxeSugcqyzbPLGSz9YPMcjiqQNzdxVSZ\n" +
            "wQIhAvugKadgPe0wuL7DaWHUEGIhYO5pDt9F5oq6cJbUr1EALzYB2xWIBa7gn5Dk\n" +
            "h0iPy9+r0neFHB23sKZICfvE1Ykzn2YLuMs3TtGVpk5MvZ55JVKH\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey="-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiGpmTq2kEVA4ddpy31iM\n" +
            "HA8q89K3WQrus4khmOtYhHzo3quDnv+jRaNlcFlI1a6nVRC8t7qbXDHYHdmEccNW\n" +
            "uksIEeA4K43cGDNCsa5sRTwu56wgCIfbfqYArwScjtjg4Sb+a21mxyiBkesNp+3e\n" +
            "jHJlIx3mp8bPIeE8O6Hh9WU4ojeFXLel8zcJEnAeeRG/f8/y8b+xRaRP6YwCW08v\n" +
            "XAd5RaQVUZSdGX63nsAH56WwQQEmID2Tc2JlJal/ahEoOn3xs/GcanJ3LKDYoMh5\n" +
            "jcM7jqXCLQzDpv4V7TOEvPnTNvDGTdzUDcOny3tal3oL4ruEPU5LUruMTEdNlL/1\n" +
            "MQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter tokenConverter(){
        JwtAccessTokenConverter converter =new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(tokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read","write")
                .authorizedGrantTypes("password","refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenConverter());
    }
}
