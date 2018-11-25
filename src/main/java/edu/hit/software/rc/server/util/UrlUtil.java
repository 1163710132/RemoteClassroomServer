package edu.hit.software.rc.server.util;

import com.google.gson.Gson;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

public class UrlUtil {
    private final Gson gson;

    public UrlUtil(){
        gson = new Gson();
    }

    public String toJsonString(Map<String, Object> params){
        return gson.toJson(params);
    }

    public String createGet(String path, Map<String, Object> params){
        if(params.size() == 0){
            return path;
        }else{
            StringBuilder builder = new StringBuilder(path);
            for(var entry : params.entrySet()){
                if(entry.getValue().getClass().isArray()){
                    Object[] array = (Object[])entry.getValue();
                    for(Object object : array){
                        builder.append(builder.length() == path.length() ? '?' : '&');
                        builder.append(URLEncoder.encode(entry.getKey() + "[]"))
                                .append('=')
                                .append(URLEncoder.encode(object.toString()));
                    }
                }
                builder.append(builder.length() == path.length() ? '?' : '&');
                builder.append(URLEncoder.encode(entry.getKey()))
                        .append('=')
                        .append(URLEncoder.encode(entry.getValue().toString()));
            }
            return builder.toString();
        }
    }

    public <T> T postBlock(String url, Map<String, Object> params, Class<T> type) throws IOException{
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.connect();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        var output = urlConnection.getOutputStream();
        Writer writer = new OutputStreamWriter(output);
        writer.write(toJsonString(params));
        writer.flush();
        T result = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), type);
        return result;
    }

    public <T> T getBlock(String url, Class<T> type) throws IOException{
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.connect();
        T result = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), type);
        return result;
    }
}
