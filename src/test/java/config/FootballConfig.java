package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.BeforeClass;

public class FootballConfig {

    @BeforeClass
    public static void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.football-data.org")
                .setBasePath("/v2")
                .addHeader("X-Auth-Token", "8df69ce914ac49e5a64a485ad355ef56")
                .addHeader("X-Response-Control", "minified")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

        RestAssured.responseSpecification=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    }

