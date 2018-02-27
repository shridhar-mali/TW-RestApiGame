/**
 * 
 */
package com.tw.game.json;

import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.tw.game.model.Products;

/**
 * @author Shridhar
 *
 */
public class JsonInputProcessor {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

	public Products extractProductsFromJson(String jsonInput) throws IOException {
		ObjectReader reader = OBJECT_MAPPER.readerFor(Products.class);
		return reader.readValue(jsonInput);
	}

}
