/**
 * 
 */
package com.tw.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shridhar
 *
 */
public class Products extends ArrayList<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6326188483218246341L;

	public long count() {
		return size();
	}

	public long activeCount() {
		return stream().filter(product -> product.isActive()).count();
	}

	public long totalActiveProductsValue() {
		return stream().filter(product -> product.isActive()).mapToLong(product -> product.price).sum();
	}

	public Map<String, Integer> getCategoryToProductCountMap() {
		final Map<String, Integer> categoryToProductCountMap = new HashMap<>();

		stream().filter(product -> product.isActive()).forEach((product) -> {
			categoryToProductCountMap.compute(product.category, (category, count) -> (count == null) ? 1 : count++);
		});
		return sortByCategory(categoryToProductCountMap);
	}

	private Map<String, Integer> sortByCategory(Map<String, Integer> categoryToProductCountMap) {
		Map<String, Integer> formatedCategoryToProductCountMap = new LinkedHashMap<>();
		List<String> categories = new ArrayList<>(categoryToProductCountMap.keySet());
		Collections.sort(categories, String.CASE_INSENSITIVE_ORDER);
		categories.forEach(category -> {
			formatedCategoryToProductCountMap.put(category, categoryToProductCountMap.get(category));
		});
		return Collections.unmodifiableMap(formatedCategoryToProductCountMap);

	}
}
