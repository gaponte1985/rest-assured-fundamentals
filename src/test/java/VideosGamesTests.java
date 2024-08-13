import config.VideoGameConfig;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objects.VideoGame;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class VideosGamesTests extends VideoGameConfig {

    @Test
    public void getAllGames() {
        given()
                .when()
                .get(VideoGameEndpoints.ALL_VIDEOS_GAMES)
                .then();
    }

    @Test
    public void createNewGameByJson() {
        String gameBodyJson = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"Mario\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2012-05-04\",\n" +
                "  \"reviewScore\": 85\n" +
                "}";
        given()
                .body(gameBodyJson)
                .contentType("application/json")

                .when()
                .post(VideoGameEndpoints.ALL_VIDEOS_GAMES)
                .then();
    }


    @Test
    public void createNewGameByXML() {
        String gameBodyXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>Mario</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2012-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";
        given()
                .body(gameBodyXML)
                .contentType("application/xml")
                .accept("application/xml")
                .when()
                .post(VideoGameEndpoints.ALL_VIDEOS_GAMES)
                .then();
    }

    @Test
    public void updateGame() {
        String gameBodyJson = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"Mario\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2012-05-04\",\n" +
                "  \"reviewScore\": 85\n" +
                "}";
        given()
                .body(gameBodyJson)
                .contentType("application/json")

                .when()
                .put("videogame/3")
                .then();
    }

    @Test
    public void deleteGme() {
        given()
                .accept("text/plain")
                .when()
                .delete("/videogame/3").then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEOS_GAMES)
                .then();
    }

    @Test
    public void testVideoGameSerializationByJSON() {
        VideoGame videoGame = new VideoGame("Shgoter", "MyAwesomeGame", "Mature", "2018-04-04", 99);

        given()
                .contentType("application/json")
                .accept("application/json")
                .body(videoGame)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEOS_GAMES)
                .then();
    }

    @Test
    public void testVideoGameSchemaXML() {
        given()
                .pathParam("videoGameId", 5)
                .accept("application/xml")
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEOS_GAMES)
                .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }

    @Test
    public void testVideosSchemaJson() {
        given()
                .pathParam("videoGameId", 5)
                .accept("application/json")
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEOS_GAMES)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void convertJsonToPojo() {
        Response response=
                given()
                .pathParam("videoGameId", 5)
                        .when()
                        .contentType("application/json")
                        .accept("application/json")
                        .get(VideoGameEndpoints.SINGLE_VIDEOS_GAMES);
        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame);
    }

    @Test
    public void captureResponseTime() {
        Long responseTime=get(VideoGameEndpoints.ALL_VIDEOS_GAMES).time();
        System.out.println(responseTime);
    }

    @Test
    public void assertOnResponseTime() {
        get(VideoGameEndpoints.ALL_VIDEOS_GAMES)
                .then()
                .time(lessThan(1000L));
    }
}
