package api_testsuites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Requests {	
	private static final String URL = "https://jsonplaceholder.typicode.com/todos/1";	
	
	@Test
	public void SearchCarer_ApiTest01() throws ClientProtocolException, IOException {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(URL);		
			HttpResponse response = client.execute(get);
			
			//Check valid response code
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	
			//Check valid response body
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}			
			JSONObject jsonResult = new JSONObject(result.toString());
			
			//Assert correct JSON structure
			Assert.assertNotNull(jsonResult);
			
			//Assert that a node called title exists
			Assert.assertTrue((Boolean) jsonResult.has("title"));
			
			//Assert that title node text is: "delectus aut autem"
			Assert.assertEquals((String) jsonResult.get("title"), "delectus aut autem");
			
			//Store the "completed" JSON node in a variable and convert it to boolean
			Assert.assertTrue((Boolean) jsonResult.get("completed") instanceof Boolean);
	  }
}
