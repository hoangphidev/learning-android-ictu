package com.hpsoft.truyentranh.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.adapters.ChapTruyenAdapter;
import com.hpsoft.truyentranh.objects.ChapTruyen;
import com.hpsoft.truyentranh.until.ConnectServer;
import com.hpsoft.truyentranh.until.ParserJSON;
import com.squareup.picasso.Picasso;

public class ChapTruyenActivity extends AppCompatActivity {
    private TextView tvTenTruyen;
    private ImageView imageView;
    private TextView tvMota;
    private ListView listViewChap;
    private AlertDialog dialogXemThem;
    private TextView tvRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_truyen);
        tvTenTruyen = findViewById(R.id.tvTenTruyen);
        imageView = findViewById(R.id.ivThumChap);
        tvMota = findViewById(R.id.tvMoTa);
        listViewChap = findViewById(R.id.lvChapTruyen);
        tvRating = findViewById(R.id.tvRating);

        String tenTruyen = getIntent().getStringExtra("tenTruyen");
        String url = getIntent().getStringExtra("linkTruyen");
        String linkIMG = getIntent().getStringExtra("linkIMG");
        String rate = getIntent().getStringExtra("Rate");

        tvRating.setText(rate);

        Picasso.get()
                .load(linkIMG).fit()
//                .resize(95, 137)
                .centerCrop()
                .into(imageView);
        tvTenTruyen.setText(tenTruyen);
        new ConnectServer().getJSONChapTruyen(this, url, new ConnectServer.VolleyCallBack() {
            @Override
            public void getJSON(String json) {
                final ChapTruyen chapTruyen = new ParserJSON(json).getChapTruyen();
                tvMota.setText(chapTruyen.getMoTa());
                ChapTruyenAdapter adapter = new ChapTruyenAdapter(ChapTruyenActivity.this, R.layout.chap_item, chapTruyen.getListChap());
                listViewChap.setAdapter(adapter);
                tvMota.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buildDialogXemThem(chapTruyen.getMoTa());
                        dialogXemThem.show();
                    }
                });
                listViewChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String url = chapTruyen.getListChap().get(i).getLinkChap();
                        Intent intent = new Intent(ChapTruyenActivity.this, DocTruyenActivity.class);
                        intent.putExtra("urlChap",url);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void buildDialogXemThem(String mota){
        AlertDialog.Builder builder = new AlertDialog.Builder(ChapTruyenActivity.this);

        builder.setTitle("Nội dung mô tả");
        builder.setMessage(mota);

        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogXemThem.dismiss();
            }
        });

        dialogXemThem = builder.create();
    }
}
