package malikin.github.io.blitz.statistics;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {

    private final static String HOST = "http://localhost";

    @LocalServerPort
    private int port;

    @Test
    public void getNotesList() {
        given().when().get(HOST + ":" + port + "/events").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void postNewNoteAndGet() {
        final String noteBody = "{\"name\": \"view\", \"externalUid\": 3001}";

        given().when().body(noteBody).contentType(ContentType.JSON)
                .post(HOST + ":" + port + "/events/").then().statusCode(HttpStatus.CREATED.value());
        given().when()
                .get(HOST + ":" + port + "/events?externalUid=3001").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void postNewNoteInvalid() {
        final String noteBody = "{\"name\": \"\"}";

        given().when().body(noteBody).contentType(ContentType.JSON)
                .post(HOST + ":" + port + "/events/").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void incrementCreateAndIncrementCounter() {
        final String noteBody = "{\"name\": \"view\", \"externalUid\": 3002}";

        given().when().body(noteBody).contentType(ContentType.JSON)
                .post(HOST + ":" + port + "/events/").then().statusCode(HttpStatus.CREATED.value());

        given().when().body(noteBody).contentType(ContentType.JSON)
                .post(HOST + ":" + port + "/events/").then().statusCode(HttpStatus.CREATED.value());

        given().when().body(noteBody).contentType(ContentType.JSON)
                .post(HOST + ":" + port + "/events/").then().statusCode(HttpStatus.CREATED.value());

        given().when()
                .get(HOST + ":" + port + "/events?externalUid=3002").then().statusCode(HttpStatus.OK.value());

        assertEquals(
                3L,
                given().when()
                        .get(HOST + ":" + port + "/events?externalUid=3002")
                        .then()
                        .extract()
                        .jsonPath()
                        .getLong("[0].count")
        );
    }
}
