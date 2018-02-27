/**
 * 
 */
package com.tw.game.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Shridhar
 *
 */
public class GameEndPointConnector {

	private static final String USER_ID_HEADER = "userId";
	private static final String CHALLENGE_ENDPOINT_HOST = "tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com";
	private static final String HTTP_CHALLENGE_OUTPUT_ENDPOINT = "http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/output";
	private static final String HTTP_CHALLENGE_INPUT_ENDPOINT = "http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/input";
	
	private String userId;
	
	private CloseableHttpClient httpclient;

	public GameEndPointConnector(String userId) {
		this.userId = userId;
		httpclient = HttpClients.createMinimal();
	}

	public String getInput() throws ClientProtocolException, IOException {
		try (CloseableHttpResponse httpResponse = httpclient.execute(buildGetRequest())) {
			return extractResponseString(httpResponse);
		}
	}

	public String postOutput(String jsonOutput) throws ClientProtocolException, IOException {
		try (CloseableHttpResponse httpResponse = httpclient.execute(buildPostRequest(jsonOutput))) {
			return extractResponseString(httpResponse);
		}

	}

	private static String extractResponseString(CloseableHttpResponse httpResponse) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
			String output;
			builder = new StringBuilder();
			while (null != (output = br.readLine())) {
				builder.append(output);
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return builder.toString();
	}

	private HttpUriRequest buildPostRequest(String jsonOutput) {
		RequestBuilder requestUriBuilder = RequestBuilder
				.post(HTTP_CHALLENGE_OUTPUT_ENDPOINT);
		addTWSecurityHeaders(requestUriBuilder);
		requestUriBuilder.setEntity(new StringEntity(jsonOutput, ContentType.APPLICATION_JSON));
		return requestUriBuilder.build();
	}

	private HttpUriRequest buildGetRequest() {
		RequestBuilder requestBuilder = RequestBuilder
				.get(HTTP_CHALLENGE_INPUT_ENDPOINT);
		addTWSecurityHeaders(requestBuilder);
		return requestBuilder.build();
	}

	private void addTWSecurityHeaders(RequestBuilder requestBuilder) {
		requestBuilder.addHeader(USER_ID_HEADER, userId).addHeader("Host",
				CHALLENGE_ENDPOINT_HOST);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		httpclient.close();
	}

}
