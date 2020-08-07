package tech.hoangphi.demo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import tech.hoangphi.demo.Adapter.WeatherAdapter;
import tech.hoangphi.demo.Model.Weather;
import tech.hoangphi.demo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rcv_weathers;
    WeatherAdapter weatherAdapter;
    ArrayList<Weather> arrWeathers;
    EditText edt_dia_diem, edt_nhiet_do;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_dia_diem = findViewById(R.id.edt_dia_diem);
        edt_nhiet_do = findViewById(R.id.edt_nhiet_do);
        rcv_weathers = findViewById(R.id.rcv_weathers);

        arrWeathers = new ArrayList<>();
        arrWeathers.add(new Weather("Ha noi", 10));
        arrWeathers.add(new Weather("Vinh Phuc", 21));
        arrWeathers.add(new Weather("Phu Tho", 31));

        weatherAdapter = new WeatherAdapter(MainActivity.this, arrWeathers);
        rcv_weathers.setAdapter(weatherAdapter);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                AddWeather();
                break;
            case R.id.btn_view:
                Weather weather = arrWeathers.get(0);
                for (int i = 1 ; i<arrWeathers.size() ; i++){
                    if (weather.getNhietdo() > arrWeathers.get(i).getNhietdo()){
                        weather = arrWeathers.get(i);
                    }
                }
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("data", weather);
                startActivity(intent);
                break;
        }
    }

    private void AddWeather(){
        String dia_diem = edt_dia_diem.getText().toString();
        if (dia_diem.trim().isEmpty()){
            edt_dia_diem.setError("Không được bỏ trống địa điểm");
            return;
        }
        if (edt_nhiet_do.getText().toString().trim().isEmpty()){
            edt_nhiet_do.setError("Không được bỏ trống nhiệt độ");
            return;
        }else {
            int nhiet_do = Integer.parseInt(edt_nhiet_do.getText().toString());
            arrWeathers.add(new Weather(dia_diem, nhiet_do));
            weatherAdapter.notifyDataSetChanged();
            edt_dia_diem.setText("");
            edt_nhiet_do.setText("");
            edt_dia_diem.requestFocus();
        }

    }
}
