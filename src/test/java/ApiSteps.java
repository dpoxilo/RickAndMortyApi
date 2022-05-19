import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class ApiSteps {

    public static String speciesMortySmith;
    public static String locationMortySmith;
    public static String lastEpisode;
    public static String lastCharacter;
    public static String speciesYoungJerry;
    public static String locationYoungJerry;

    public static void findLastEpisodeMortySmith() {
        Response infoAboutMortySmith = given()
                .baseUri("https://rickandmortyapi.com/api")
                .contentType(ContentType.JSON)
                .when().get("/character/?name=Morty Smith")
                .then()
                .assertThat()
                .statusCode(200)
                .and().body("results.name", notNullValue())
                .extract().response();
        JsonPath jsonPath = infoAboutMortySmith.jsonPath();
        speciesMortySmith = jsonPath.get("results[0].species");
        System.out.println("Раса Morty Smith: " + speciesMortySmith);
        locationMortySmith = jsonPath.get("results[0].location.name");
        System.out.println("Местоположение Morty Smith: " + locationMortySmith);
        lastEpisode = jsonPath.get("results[0].episode[50]");
        System.out.println("Последний эпизод: " + lastEpisode);
    }

    public static void findLastCharacterLastEpisodeMortySmith() {
        Response infoAboutLastEpisodeMortySmith = given()
                .contentType(ContentType.JSON)
                .when().get(lastEpisode)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        JsonPath jsonPath = infoAboutLastEpisodeMortySmith.jsonPath();
        lastCharacter = jsonPath.get("characters[41]");
        System.out.println("Последний персонаж: " + lastCharacter);
    }

    public static void findInfoAboutLastCharacterLastEpisodeMortySmith() {
        Response infoAboutLastCharacterLastEpisodeMortySmith = given()
                .contentType(ContentType.JSON)
                .when().get(lastCharacter)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        JsonPath jsonPath = infoAboutLastCharacterLastEpisodeMortySmith.jsonPath();
        speciesYoungJerry = jsonPath.get("species");
        locationYoungJerry = jsonPath.get("location.name");
        System.out.println("Раса Young Jerry: " + speciesYoungJerry);
        System.out.println("Местоположение Young Jerry: " + locationYoungJerry);
    }

    public static void assertSpeciesAndLocation() {
        if (speciesMortySmith.equals(speciesYoungJerry)) {
            System.out.println("Персонажи одной расы");
        } else {
            System.out.println("Персонажи не одной расы");
        }

        if (locationMortySmith.equals(locationYoungJerry)) {
            System.out.println("Персонажи находятся в одном месте");
        } else {
            System.out.println("Персонажи находятся в разных местах");
        }
    }
}
