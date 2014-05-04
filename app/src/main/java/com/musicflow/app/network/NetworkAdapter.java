package com.musicflow.app.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

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

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicflow.app.R;
import com.musicflow.app.data.Authorization;
import com.musicflow.app.data.AuthorizationRequest;
import com.musicflow.app.data.BaseJson;
import com.musicflow.app.login.LoginActivity;
import com.musicflow.app.mappers.AuthorizationMapper;
import com.musicflow.app.mappers.CommonMapper;

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
    protected Context context;

    public NetworkAdapter(Context context, CommonMapper mapper, RequestType type, Map<String, String> headers, BaseJson json) {
        super();
        this.mapper = mapper;
        this.type = type;
        this.headers = headers;
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        this.json = json;
        this.context = context;
    }

    public NetworkAdapter(Context context, CommonMapper mapper, RequestType type, Map<String, String> headers, String body, BaseJson response) {
        this(context, mapper, type, headers, response);
        try {
            this.body = new StringEntity(body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public NetworkAdapter(Context context, CommonMapper mapper, RequestType type, Map<String, String> headers, BaseJson body, BaseJson response) {
        this(context, mapper, type, headers, response);
        try {
            ObjectMapper jsonSerializer = new ObjectMapper();
            String baseJsonString = jsonSerializer.writeValueAsString(body);
            this.body = new StringEntity(baseJsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected Boolean authRequired() {
        return false;
    }

    private void makeRefreshRequest(AuthorizationRequest authorizationRequest) {
        try {
            Authorization authorization = new Authorization();
            ObjectMapper jsonSerializer = new ObjectMapper();
            String baseJsonString = jsonSerializer.writeValueAsString(authorizationRequest);
            StringEntity body = new StringEntity(baseJsonString);

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString;

            HttpPost post = new HttpPost(UrlFactory.obtainToken());
            for (String key : headers.keySet()) {
                post.addHeader(key, headers.get(key));
            }
            post.setEntity(body);
            response = httpclient.execute(post);

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

            authorization.fillIn(new AuthorizationMapper().parseJson(responseString));

            String preferencesKey = context.getString(R.string.user_preferences_key);
            context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).edit().putString("access_token", authorization.getResult().getAccessToken()).commit();
            context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).edit().putString("refresh_token", authorization.getResult().getRefreshToken()).commit();
            context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).edit().putLong("access_expires_at", System.currentTimeMillis() + (1000 * authorization.getResult().getExpiresIn())).commit();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            String answer = e.getLocalizedMessage();
            Log.d("network manager", answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        if (authRequired()) {
            String preferencesKey = context.getString(R.string.user_preferences_key);
            String accessToken = context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).getString("access_token", null);

            if (accessToken == null) {
                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);
                cancel(true);
            }
        }
    }

    @Override
    protected String doInBackground(String... uri) {
        String responseString = null;
        if (!isCancelled()) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;

            if (authRequired()) {
                String preferencesKey = context.getString(R.string.user_preferences_key);
                Long accessExpires = context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).getLong("access_expires_at", System.currentTimeMillis());
                if (accessExpires < System.currentTimeMillis()) {
                    String code = context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).getString("refresh_token", "");
                    AuthorizationRequest body = new AuthorizationRequest(UrlFactory.clientSecret(), UrlFactory.clientID(), "http://www.musicflow.com", code, "refresh_token", true);
                    makeRefreshRequest(body);
                }

                headers.put("Authorization", "Bearer " + context.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE).getString("access_token", ""));
            }

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
