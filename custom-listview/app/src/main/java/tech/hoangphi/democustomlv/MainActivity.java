package tech.hoangphi.democustomlv;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv;
    private NguoiAdapter nguoiAdapter;
    private EditText edt_ten, edt_cv;
    private int vi_tri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv_sv);
        edt_ten = findViewById(R.id.edt_ten);
        edt_cv = findViewById(R.id.edt_cv);

        nguoiAdapter = new NguoiAdapter();
        lv.setAdapter(nguoiAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vi_tri = position;
                Nguoi nguoi = (Nguoi) nguoiAdapter.getItem(position);
                edt_ten.setText(nguoi.getTen());
                edt_cv.setText(nguoi.getCv());
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Xác nhận xóa ? ");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nguoiAdapter.xoa(position);
                        xoaDuLieu();
                        Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        findViewById(R.id.btn_them).setOnClickListener(this);
        findViewById(R.id.btn_sua).setOnClickListener(this);
    }

    private void xoaDuLieu() {
        edt_ten.setText("");
        edt_ten.requestFocus();
        edt_cv.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_them) {
            String ten = "";
            String cv = "";
            if (edt_ten.getText().toString().isEmpty()) {
                edt_ten.setError("Không được để trống");
            } else {
                ten = edt_ten.getText().toString().trim();
            }

            if (edt_cv.getText().toString().isEmpty()) {
                edt_cv.setError("Không được để trống");
            } else {
                cv = edt_cv.getText().toString().trim();
            }
            nguoiAdapter.them(new Nguoi(ten, cv, R.drawable.adi));
            Toast.makeText(this, "Thêm thành công " + ten, Toast.LENGTH_SHORT).show();
            xoaDuLieu();
        } else {
            String ten = "";
            String cv = "";
            if (edt_ten.getText().toString().isEmpty()) {
                edt_ten.setError("Không được để trống");
            } else {
                ten = edt_ten.getText().toString().trim();
            }

            if (edt_cv.getText().toString().isEmpty()) {
                edt_cv.setError("Không được để trống");
            } else {
                cv = edt_cv.getText().toString().trim();
            }
            nguoiAdapter.sua(vi_tri, new Nguoi(ten, cv, R.drawable.adi));
            Toast.makeText(this, "Sửa thành công " + ten, Toast.LENGTH_SHORT).show();
            xoaDuLieu();
        }
    }
}
