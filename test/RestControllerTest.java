import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.Test;

import play.libs.Json;
import play.libs.ws.WS;

import com.fasterxml.jackson.databind.JsonNode;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class RestControllerTest {

	JsonNode identifications;

	@Test
	public void getIdentifications() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/identifications").get().get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void removeIdentification() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteIdent/1").get().get(10000).getStatus(), OK);
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteIdent/2").get().get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void removeCompany() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteComp/1").get().get(10000).getStatus(), OK);
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteComp/2").get().get(10000).getStatus(), OK);
			}
		});

	}
	
			
	@Test
	public void postIdentification() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				JsonNode company = Json.parse("{\"compId\": 1, \"compName\": \"Test Bank\", \"slaTime\": 60, \"slaPercentage\": 0.9, \"currentSlaPercentage\": 0.95}");
				assertEquals(WS.url("http://localhost:3333/api/v1/addCompany").post(company).get(10000).getStatus(), OK);
				
				JsonNode company1 = Json.parse("{\"compId\": 1, \"compName\": \"Test Bank2\", \"slaTime\": 60, \"slaPercentage\": 0.9, \"currentSlaPercentage\": 0.90}");
				assertEquals(WS.url("http://localhost:3333/api/v1/addCompany").post(company1).get(10000).getStatus(), OK);
				
				JsonNode identification = Json.parse("{\"idntId\": 1, \"compId\": 1, \"idntName\": \"Peter Huber\"}");
				assertEquals(WS.url("http://localhost:3333/api/v1/startIdentification").post(identification).get(10000).getStatus(), OK);
				
				JsonNode identification2 = Json.parse("{\"idntId\": 1, \"compId\": 1, \"idntName\": \"Peter Huber\"}");
				assertEquals(WS.url("http://localhost:3333/api/v1/startIdentification").post(identification2).get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void getIdentifications1() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/identifications").get().get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void removeIdentification2() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteIdent/1").get().get(10000).getStatus(), OK);
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteIdent/2").get().get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void removeCompany2() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteComp/1").get().get(10000).getStatus(), OK);
				assertEquals(WS.url("http://localhost:3333/api/v1/deleteComp/2").get().get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void postIdentification2() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				JsonNode company = Json.parse("{\"compId\": 1, \"compName\": \"Test Bank\", \"slaTime\": 60, \"slaPercentage\": 0.9, \"currentSlaPercentage\": 0.95}");
				assertEquals(WS.url("http://localhost:3333/api/v1/addCompany").post(company).get(10000).getStatus(), OK);
				
				JsonNode company1 = Json.parse("{\"compId\": 1, \"compName\": \"Test Bank2\", \"slaTime\": 120, \"slaPercentage\": 0.8, \"currentSlaPercentage\": 0.95}");
				assertEquals(WS.url("http://localhost:3333/api/v1/addCompany").post(company1).get(10000).getStatus(), OK);
				
				JsonNode identification = Json.parse("{\"idntId\": 1, \"compId\": 1, \"idntName\": \"Peter Huber\"}");
				assertEquals(WS.url("http://localhost:3333/api/v1/startIdentification").post(identification).get(10000).getStatus(), OK);
				
				JsonNode identification2 = Json.parse("{\"idntId\": 1, \"compId\": 1, \"idntName\": \"Peter Huber\"}");
				assertEquals(WS.url("http://localhost:3333/api/v1/startIdentification").post(identification2).get(10000).getStatus(), OK);
			}
		});

	}
	
	@Test
	public void getIdentifications3() {
		running(testServer(3333, fakeApplication(inMemoryDatabase())), new Runnable() {
			@Override
			public void run() {
				assertEquals(WS.url("http://localhost:3333/api/v1/identifications").get().get(10000).getStatus(), OK);
			}
		});

	}
	
}
