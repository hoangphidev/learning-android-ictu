package com.example.huehashop.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import com.example.huehashop.R;
import com.example.huehashop.adapter.GiohangAdapter;

import java.text.NumberFormat;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView txttongtien, toolbar_title_detail;
    Button btnthanhtoan, btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToolbar();
        //CheckData();
        EventUltil();
    }

    private void EventUltil() {
        int tongtien = 0;
        for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        txttongtien.setText("Tổng tiền: " + NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(tongtien));

        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void CheckData() {
        if (MainActivity.manggiohang.size() < 0) {
            giohangAdapter.notifyDataSetChanged();
            lvgiohang.setVisibility(View.INVISIBLE);
        } else {
            giohangAdapter.notifyDataSetChanged();
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbargiohang.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void Anhxa() {
        lvgiohang = findViewById(R.id.listviewgiohang);
        toolbar_title_detail = findViewById(R.id.toolbar_title_detail);
        txttongtien = findViewById(R.id.textviewtongtien);
        btnthanhtoan = findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua = findViewById(R.id.buttontieptucmuahang);
        toolbargiohang = findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(GioHangActivity.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);

        Typeface mMedium = ResourcesCompat.getFont(getApplicationContext(), R.font.mm);
        btntieptucmua.setTypeface(mMedium);
        btnthanhtoan.setTypeface(mMedium);
        toolbar_title_detail.setTypeface(mMedium);
    }
}
