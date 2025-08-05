package https.github.com.FrancoBorba.integrationTests.swagger;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import https.github.com.FrancoBorba.config.TestConfigs;
import https.github.com.FrancoBorba.integrationTests.testContainers.AbstractIntegrationTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest{

	@Test
	void shouldDisplaySwaggerUIPage() {
   var content = given().basePath("/swagger-ui/index.html").
       port(TestConfigs.SERVER_PORT).
    when().
        get().
    then().
        statusCode(200)
    .extract()
      .body()
       .asString();

       assertTrue(content.contains("Swaguer UI"));
	}

}
