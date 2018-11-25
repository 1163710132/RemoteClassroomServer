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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

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

    public <T> T post(String url, Map<String, Object> params, Class<T> type) throws IOException{
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

    public <T> T get(String url, Map<String, Object> params, Class<T> type) throws IOException{
        URLConnection urlConnection = new URL(createGet(url, params)).openConnection();
        urlConnection.connect();
        T result = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), type);
        return result;
    }

    public <T> Future<T> postAsync(String url, Map<String, Object> params, Class<T> type) throws IOException{
        var task = new FutureTask<>(()->post(url, params, type));
        new Thread(task).run();
        return task;
    }

    public <T> Future<T> getAsync(String url, Map<String, Object> params, Class<T> type) throws IOException{
        var task = new FutureTask<>(()->get(url, params, type));
        new Thread(task).run();
        return task;
    }

    public MapBuilder<String, Object> paramsBuilder(){
        return new MapBuilder<>();
    }

    public static class MapBuilder<K, V>{
        private Map<K, V> map;

        public MapBuilder(){
            map = new HashMap<>();
        }

        public MapBuilder<K, V> put(K key, V value){
            map.put(key, value);
            return this;
        }

        public Map<K, V> build(){
            var map = this.map;
            this.map = null;
            return map;
        }
    }

    public static void main(String[] args) throws Exception {
        UrlUtil urlUtil = new UrlUtil();
        var id = urlUtil.get("http://localhost:8080/account/register",
                urlUtil.paramsBuilder().put("username", "chenjs")
                        .put("password", "1144").build(), long.class);
        System.out.println(id);
    }
}
