package lucas.miranda.rest.tests.refac;

import static io.restassured.RestAssured.*;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import lucas.miranda.rest.core.BaseTest;

public class AuthTest extends BaseTest
	{
		@Test
		public void naoDeveAcessarAPISemToken()
			{
				FilterableRequestSpecification req = (FilterableRequestSpecification) RestAssured.requestSpecification;
				req.removeHeader("Authorization");
				given()
				.when()
					.get("/contas")
				.then()
					.statusCode(401)
				;
			}
		
		}
