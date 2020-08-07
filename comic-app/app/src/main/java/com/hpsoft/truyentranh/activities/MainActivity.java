package com.hpsoft.truyentranh.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.adapters.TheLoaiAdapter;
import com.hpsoft.truyentranh.fragments.ListTruyenFragment;
import com.hpsoft.truyentranh.objects.TheLoai;
import com.hpsoft.truyentranh.until.ConnectServer;
import com.hpsoft.truyentranh.until.ParserJSON;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvTheLoai;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        lvTheLoai = findViewById(R.id.lvTheLoai);

        new ConnectServer().getJSONTheLoai(MainActivity.this, new ConnectServer.VolleyCallBack() {
            @Override
            public void getJSON(String json) {
                //Log.d(TAG + "json the loai: ", json);
                final List<TheLoai> listTheLoais = new ParserJSON(json).getListTheLoai();
                TheLoaiAdapter adapter = new TheLoaiAdapter(MainActivity.this, R.layout.theloai_item, listTheLoais);
                lvTheLoai.setAdapter(adapter);
                String url = listTheLoais.get(0).getLinkTheLoai();
                String tenTheLoai = listTheLoais.get(0).getTenTheLoai();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                bundle.putString("tenTheLoai", tenTheLoai);
                ListTruyenFragment listTruyenFragment = new ListTruyenFragment();
                listTruyenFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, listTruyenFragment).commit();
                lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String url = listTheLoais.get(i).getLinkTheLoai();
                        String tenTheLoai = listTheLoais.get(i).getTenTheLoai();
                        Bundle bundle = new Bundle();
                        bundle.putString("url", url);
                        bundle.putString("tenTheLoai", tenTheLoai);
                        ListTruyenFragment listTruyenFragment = new ListTruyenFragment();
                        listTruyenFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, listTruyenFragment).commit();
                        drawerLayout.closeDrawers();
                    }
                });
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


}
