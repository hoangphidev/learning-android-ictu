package tech.hoangphi.demo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import tech.hoangphi.demo.Model.Weather;
import tech.hoangphi.demo.R;

public class SecondActivity extends AppCompatActivity {
    TextView tv_hien_thi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv_hien_thi = findViewById(R.id.tv_hien_thi);
        Weather weather = (Weather) getIntent().getSerializableExtra("data");
        tv_hien_thi.setText(weather.getDiadiem() + "\t\n" + weather.getNhietdo() + "ºC");
    }
}
