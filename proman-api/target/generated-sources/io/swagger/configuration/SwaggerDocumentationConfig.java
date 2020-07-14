package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-02-19T07:36:48.924+05:30")

@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Authentication API")
            .description("## API of the Authentication Service  This specification describes the endpoints, request/response model of the Authentication API. This API provides REST resources to authenticate and authorize users of Proman.  The resources should only be used over HTTPS (SSL) and can be accessed via the following base URL: https://proman.io/api/v1/ ## API usage guidelines  This section describes general rules and guidelines for using REST based services offered by Proman.   * All resources can only be accessed via HTTPS and a respective HTTP Basic Authentication header is also required.   * Proman reserves the right to add new parameters without further notice (in request and response).   * Proman reserves the right to add new error codes without further notice. Proman will thereby stick to the HTTP STATUS CODES as defined by W3C.   * Proman reserves the right to enhance enumerations by new values.   * Proman reserves the right to increase the \"maxItem\" settings of arrays without further notice.    Each consumer needs to tolerant about changes to the API as much as possible ([see Tolerant Reader Pattern](http://martinfowler.com/bliki/TolerantReader.html))  ## Business definitions A user who is trying to login is a registered user whose authentication has to be validated before authorizing them with the right access. Once authenticated and authorized successfully, an access token in JWT standard is generated by the backend system containing following information of the logged in user:   * header - meta information of generated access token   * payload - claims information of authenticated user   * signature - digital signature to verify that the token is not tampered along the way  Read https://jwt.io/introduction/ for more information on JWT standard. ## Release Notes ### 1.0.0 * Initial version ")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", ""))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                    .build()
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}
