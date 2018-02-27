/**
 * 
 */
package com.tw.game.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tw.game.model.Products;

/**
 * @author Shridhar
 *
 */
public class JsonOutputWrapper {

	private Products products;

	private static final JsonNodeFactory JSON_NODE_FACTORY = JsonNodeFactory.instance;

	public JsonOutputWrapper(Products products) {
		this.products = products;
	}

	public String productsCount() {
		ObjectNode rootNode = JSON_NODE_FACTORY.objectNode();
		return rootNode.put("count", products.count()).toString();
	}

	public String activeProductsCount() {
		ObjectNode rootNode = JSON_NODE_FACTORY.objectNode();
		return rootNode.put("count", products.activeCount()).toString();
	}

	public String totalValueOfActiveProducts() {
		ObjectNode rootNode = JSON_NODE_FACTORY.objectNode();
		return rootNode.put("totalValue", products.totalActiveProductsValue()).toString();
	}

	public String categoryWiseActiveProductCount() {
		ObjectNode rootNode = JSON_NODE_FACTORY.objectNode();
		products.getCategoryToProductCountMap().forEach((category, count) -> rootNode.put(category, count));
		return rootNode.toString();
	}

}
