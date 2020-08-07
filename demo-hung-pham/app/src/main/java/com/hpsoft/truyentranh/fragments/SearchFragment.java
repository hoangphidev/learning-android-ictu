package com.hpsoft.truyentranh.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.activities.ChapTruyenActivity;
import com.hpsoft.truyentranh.adapters.ListTruyenAdapter;
import com.hpsoft.truyentranh.objects.Truyen;
import com.hpsoft.truyentranh.until.ConnectServer;
import com.hpsoft.truyentranh.until.ParserJSON;

import java.util.List;

public class SearchFragment extends Fragment {
    private View view;
    ListView lv_search;
    ListTruyenAdapter listTruyenAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        lv_search = view.findViewById(R.id.lv_search);
        String key = getArguments().getString("key");

        new ConnectServer().getSearch(getContext(), key, new ConnectServer.VolleyCallBack() {
            @Override
            public void getJSON(String json) {
                Log.d("keySearch", json);
                final List<Truyen> truyenList = new ParserJSON(json).getListTruyen();
                listTruyenAdapter = new ListTruyenAdapter(getActivity(), R.layout.truyen_item,truyenList);
                lv_search.setAdapter(listTruyenAdapter);
                lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String url = truyenList.get(i).getLinkTruyen();
                        String tenTruyen = truyenList.get(i).getTenTruyen();
                        String linkIMG = truyenList.get(i).getLinkImage();
                        String rate = truyenList.get(i).getRating();
                        Intent intent = new Intent(getActivity(), ChapTruyenActivity.class);
                        intent.putExtra("linkIMG",linkIMG);
                        intent.putExtra("tenTruyen",tenTruyen);
                        intent.putExtra("linkTruyen",url);
                        intent.putExtra("Rate",rate);
                        startActivity(intent);
                    }
                });
            }
        });


        return view;
    }
}
