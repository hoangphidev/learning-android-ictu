package com.hpsoft.truyentranh.until;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ConnectServer {
    //private static final String host = "http://sukastore.000webhostapp.com/beeng/";
    private static final String host = "http://192.168.234.2/learning-php/regex/";

    public void getJSONTheLoai(Context context, final VolleyCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, host + "gettheloai.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.getJSON(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public void getJSONTruyenTheoTheLoai(Context context, String urlTheLoai, final VolleyCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, host + "gettruyentheotheloai.php?url=" + urlTheLoai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.getJSON(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public void getJSONChapTruyen(Context context, String urlChapTruyen, final VolleyCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, host + "getchaptruyen.php?url=" + urlChapTruyen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.getJSON(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public void getJSONDocTruyen(Context context, String urlAnhTruyen, final VolleyCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, host + "getdoctruyen.php?url=" + urlAnhTruyen, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.getJSON(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public void getSearch(Context context, String key, final VolleyCallBack callBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, host + "gettimkiem.php?key=" + key, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.getJSON(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("loiSearch", error.getMessage());
            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public interface VolleyCallBack {
        void getJSON(String json);
    }
}
