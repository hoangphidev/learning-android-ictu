package com.hpsoft.truyentranh.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.adapters.DocTruyenAdapter;
import com.hpsoft.truyentranh.objects.AnhTruyen;
import com.hpsoft.truyentranh.until.ConnectServer;
import com.hpsoft.truyentranh.until.ParserJSON;

import java.util.List;

public class DocTruyenActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(DocTruyenActivity.this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(DocTruyenActivity.this, LinearLayoutManager.HORIZONTAL,false));
        //recyclerView.setLayoutManager(new GridLayoutManager(DocTruyenActivity.this, 1, GridLayoutManager.HORIZONTAL, false));

        String url = getIntent().getStringExtra("urlChap");
        new ConnectServer().getJSONDocTruyen(this, url, new ConnectServer.VolleyCallBack() {
            @Override
            public void getJSON(String json) {
                final List<AnhTruyen> anhTruyenList = new ParserJSON(json).getListAnhTruyen();
                Log.d("anhtruyen", json);
                DocTruyenAdapter docTruyenAdapter = new DocTruyenAdapter(anhTruyenList);
                recyclerView.setAdapter(docTruyenAdapter);
            }
        });
    }
}
