package com.hpsoft.truyentranh.until;

import android.util.Log;

import com.hpsoft.truyentranh.objects.AnhTruyen;
import com.hpsoft.truyentranh.objects.ChapTruyen;
import com.hpsoft.truyentranh.objects.ListChap;
import com.hpsoft.truyentranh.objects.TheLoai;
import com.hpsoft.truyentranh.objects.Truyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserJSON {
    private String data;
    public ParserJSON(String json) {
        this.data = json;
    }

    public List<TheLoai> getListTheLoai(){
        List<TheLoai> listTheLoais = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String url = jsonObject.getString("url");
                String name = jsonObject.getString("name");
                listTheLoais.add(new TheLoai(name, url));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listTheLoais;
    }

    public List<Truyen> getListTruyen(){
        List<Truyen> listTruyen = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String urlTruyen = jsonObject.getString("url");
                String title = jsonObject.getString("title");
                String urlImg = jsonObject.getString("image");
                String rating = jsonObject.getString("vote");
                listTruyen.add(new Truyen(title, urlTruyen,urlImg,rating));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listTruyen;
    }
    public ChapTruyen getChapTruyen(){
       ChapTruyen chapTruyen = new ChapTruyen();
        try {
            JSONObject jsonObject1 = new JSONObject(data);
            String maintitle = jsonObject1.getString("maintitle");
            String moTa = jsonObject1.getString("caption");
            chapTruyen.setTenTruyen(maintitle);
            chapTruyen.setMoTa(moTa);
            List<ListChap> listChaps = new ArrayList<>();
            JSONArray jsonArray = jsonObject1.getJSONArray("listchap");
            for (int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String url = jsonObject.getString("url");
                String title = jsonObject.getString("title");
                String view = jsonObject.getString("view");
                String date = jsonObject.getString("date");
                listChaps.add(new ListChap(title, url, view, date));
            }
            chapTruyen.setListChap(listChaps);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chapTruyen;
    }

    public List<AnhTruyen> getListAnhTruyen(){
        List<AnhTruyen> anhTruyens = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0;i < jsonArray.length();i++){
                anhTruyens.add(new AnhTruyen(jsonArray.getString(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return anhTruyens;
    }
}
