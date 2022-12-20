package lucas.miranda.rest.tests.refac;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import lucas.miranda.rest.core.BaseTest;
import lucas.miranda.rest.utils.BarrigaUtils;

public class SaldoTeste extends BaseTest
	{		
		@Test
		public void  deveCalcularSaldoContas()
			{
				Integer CONTA_ID = BarrigaUtils.getIdContaPeloNome("Conta para saldo");
			
				given()
				.when()
					.get("/saldo")
				.then()
					.statusCode(200)
					.body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("534.00"))
				;
			}
}
