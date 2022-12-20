package lucas.miranda.rest.tests.refac;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import lucas.miranda.rest.core.BaseTest;
import lucas.miranda.rest.utils.BarrigaUtils;

public class ContasTeste extends BaseTest
	{
		@Test
		public void deveIncluirContaComSucesso()
			{
				given()
					.body("{ \"nome\": \"Conta Inserida\" }")
				.when()
					.post("/contas")
				.then()
					.statusCode(201)
				;
			}
		@Test
		public void deveAlterarContaComSucesso()
			{	
				Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para alterar");
			
				given()
					.body("{ \"nome\": \"Conta alterada\" }")
					.pathParam("id", CONTA_ID)
				.when()
					.put("/contas/{id}")
				.then()
					.statusCode(200)
					.body("nome", is("Conta alterada"))
				;
			}
		
		@Test
		public void naodeveIncluirContaComMesmoNome()
			{
				given()
					.body("{ \"nome\": \"Conta mesmo nome\" }")
				.when()
					.post("/contas")
				.then()
					.statusCode(400)
					.body("error", is("Já existe uma conta com esse nome!"))
				;
			}
		}
