package com.webservice;

import java.util.List;

import org.apache.http.NameValuePair;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

@SuppressLint("SimpleDateFormat")
public class AsyncHTTPRequestTask extends AsyncTask<Void, Void, String> {

	// sever url will be initialized on application starting loginActivity
	public static String DEFAULT_SERVER_IP = "197.28.208.85"; // 92.222.16.205
	protected static String SERVER_URL = "http://" + DEFAULT_SERVER_IP + "/pfe/rest/";
	protected static String SERVER_URL_Image = "http://" + DEFAULT_SERVER_IP + "/merchtrack/configuration/image_prod/";
	public static void updateServerIp(String serverIp) {
		AsyncHTTPRequestTask.SERVER_URL = "http://" + serverIp + "/pfe/rest/";
	}

	protected String url;
	protected String method;
	protected List<NameValuePair> params;

	/**
	 * Execute background call to the URL string in the first object using
	 * method in second object passing parameters in the second object
	 */
	@Override
	protected String doInBackground(Void... args) {
		return new HTTPRequestHelper(this.url, this.method, this.params)
				.getServerData();
	}

	/**
	 * Must be implemented by sub-classes
	 */
	@Override
	public void onPostExecute(String response) {
	}
}