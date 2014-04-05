package com.musicflow.app.network;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.mappers.CommonMapper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class NetworkAdapter extends AsyncTask<String, Void, String> {

    public enum RequestType {
        GET,
        PUT,
        POST,
        DELETE
    }

    protected CommonMapper mapper;
    protected RequestType type;
    protected Map<String, String> headers;
    protected BaseJson json;
    protected StringEntity body;

    public NetworkAdapter(CommonMapper mapper, RequestType type, Map<String, String> headers, BaseJson json) {
        super();
        this.mapper = mapper;
        this.type = type;
        this.headers = headers;
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        this.json = json;
    }

    public NetworkAdapter(CommonMapper mapper, RequestType type, Map<String, String> headers, String body, BaseJson response) {
        this(mapper, type, headers, response);
        try {
            this.body = new StringEntity(body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public NetworkAdapter(CommonMapper mapper, RequestType type, Map<String, String> headers, BaseJson body, BaseJson response) {
        this(mapper, type, headers, response);
        try {
            ObjectMapper jsonSerializer = new ObjectMapper();
            this.body = new StringEntity(jsonSerializer.writeValueAsString(body));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            switch (type) {
                case GET:
                    HttpGet get = new HttpGet(uri[0]);
                    for (String key : headers.keySet()) {
                        get.addHeader(key, headers.get(key));
                    }
                    response = httpclient.execute(get);
                    break;
                case PUT:
                    HttpPut put = new HttpPut(uri[0]);
                    for (String key : headers.keySet()) {
                        put.addHeader(key, headers.get(key));
                    }
                    put.setEntity(body);
                    response = httpclient.execute(put);
                    break;
                case POST:
                    HttpPost post = new HttpPost(uri[0]);
                    for (String key : headers.keySet()) {
                        post.addHeader(key, headers.get(key));
                    }
                    post.setEntity(body);
                    response = httpclient.execute(post);
                    break;
                case DELETE:
                    HttpDelete delete = new HttpDelete(uri[0]);
                    for (String key : headers.keySet()) {
                        delete.addHeader(key, headers.get(key));
                    }
                    response = httpclient.execute(delete);
                    break;
                default:
                    response = httpclient.execute(new HttpGet(uri[0]));
            }
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } else {
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems...
        } catch (IOException e) {
            String answer = e.getLocalizedMessage();
            Log.d("network manager", answer);
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            json.fillIn(mapper.parseJson(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
