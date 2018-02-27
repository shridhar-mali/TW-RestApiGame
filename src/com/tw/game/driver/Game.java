/**
 * 
 */
package com.tw.game.driver;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.tw.game.connector.GameEndPointConnector;
import com.tw.game.json.JsonInputProcessor;
import com.tw.game.json.JsonOutputWrapper;
import com.tw.game.model.Products;

/**
 * @author Shridhar
 *
 */
public class Game {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		GameEndPointConnector gameConnector = new GameEndPointConnector("SkcM2x3DG");
		JsonInputProcessor jsonInputProcessor = new JsonInputProcessor();
		String jsonInput = gameConnector.getInput();
		System.out.println("Input: " + jsonInput);
		Products products = jsonInputProcessor.extractProductsFromJson(jsonInput);
		JsonOutputWrapper jsonOutputWrapper = new JsonOutputWrapper(products);
		System.out.println("Output: " + gameConnector.postOutput(jsonOutputWrapper.totalValueOfActiveProducts()));

	}

}
