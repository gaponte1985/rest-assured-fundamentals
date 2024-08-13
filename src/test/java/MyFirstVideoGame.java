import config.VideoGameConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class MyFirstVideoGame extends VideoGameConfig {
    @Test
    public void myFirstTest() {
        given()
                .log().all()
                .when()
                .get("/videogame")
                .then()
                .log().all();
    }


    @Test
    public void myFirstTestWithEndPoints() {
       get(VideoGameEndpoints.ALL_VIDEOS_GAMES)
        .then()
               .log().all();
    }

}
