package com.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;

public class HTTPRequestHelper {

	private String url;
	private List<NameValuePair> params;
	private String method;

	/**
	 * RestWebService constructor
	 * 
	 * @param paramString
	 * @param paramArrayList
	 */
	@SuppressLint({ "NewApi" })
	public HTTPRequestHelper(String url, String method,
			List<NameValuePair> params) {
		this.url = url;
		this.method = method;
		this.params = params;
		if (Build.VERSION.SDK_INT >= 9)
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.permitAll().build());
	}

	/**
	 * 
	 * Get data from server
	 * 
	 * @return String
	 */
	public synchronized String getServerData() {
		String responseString = null;
		try {
			
			/**HttpParams httpParameters = new BasicHttpParams();
		    // set the timeout in milliseconds until a connection is established
		    // the default value is zero, that means the timeout is not used 
		    int timeoutConnection = 3000;
		    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		    // set the default socket timeout (SO_TIMEOUT) in milliseconds
		    // which is the timeout for waiting for data
		    int timeoutSocket = 5000;
		    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(httpParameters);*/
			
			DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
			HttpResponse response = null;
			if (this.method.equals(HttpGet.METHOD_NAME)) {
				String fullUrl = url;
				// parameters are sent within URL for GET method
				if (params != null) {
					fullUrl += "?";
					for (NameValuePair param : params) {
						fullUrl += param.getName() + "=" + param.getValue()
								+ "&";
					}
				}
				HttpGet localHttpGet = new HttpGet(fullUrl);
				response = localDefaultHttpClient.execute(localHttpGet);
			} else if (this.method.equals(HttpPost.METHOD_NAME)) {
				HttpPost localHttpPost = new HttpPost(this.url);
				if (params != null) {
					localHttpPost.setEntity(new UrlEncodedFormEntity(
							this.params));
	                
				}
				response = localDefaultHttpClient.execute(localHttpPost);
			} else if (this.method.equals(HttpPut.METHOD_NAME)) {
				HttpPut localHttpPut = new HttpPut(this.url);
				if (params != null) {
					localHttpPut
							.setEntity(new UrlEncodedFormEntity(this.params));
				}
				response = localDefaultHttpClient.execute(localHttpPut);
			}
			InputStream localInputStream = response.getEntity().getContent();
			BufferedReader localBufferedReader = new BufferedReader(
					new InputStreamReader(localInputStream, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = localBufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}
			localInputStream.close();
			responseString = sb.toString();
		} catch (Exception e) {
			//Log.e("Buffer Error", "Error converting result " + e.getMessage());
		}

		return responseString;
	}

}
