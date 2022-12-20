package lucas.miranda.rest.tests.refac.suite;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import io.restassured.RestAssured;
import lucas.miranda.rest.core.BaseTest;
import lucas.miranda.rest.tests.Movimentacao;
import lucas.miranda.rest.tests.refac.AuthTest;
import lucas.miranda.rest.tests.refac.ContasTeste;
import lucas.miranda.rest.tests.refac.SaldoTeste;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses
	(
		{
			ContasTeste.class,
			Movimentacao.class,
			SaldoTeste.class,
			AuthTest.class
		}
	)

public class Suite extends BaseTest
	{
	@BeforeClass
	public static void  login()
		{
			Map<String, String> login = new HashMap<>();
			login.put("email", "lucas.mirandasud@g");
			login.put("senha", "123456");

			String TOKEN = given()
				.body(login)
			.when()
				.post("/signin")
			.then()
				.statusCode(200)
				.extract().path("token");
			RestAssured.requestSpecification.header("Authorization","JWT " + TOKEN);
			
			RestAssured.get("/reset").then().statusCode(200);
		}
	}
