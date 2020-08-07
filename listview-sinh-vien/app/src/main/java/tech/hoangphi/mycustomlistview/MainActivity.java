package tech.hoangphi.mycustomlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SinhVienAdapter sinhVienAdapter;
    private int vi_tri;
    private ListView lv_sv;
    private EditText edt_ten, edt_tuoi;
    private Button btn_them, btn_sua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Anh xa
        lv_sv = findViewById(R.id.lv_sv);
        edt_ten = findViewById(R.id.edt_ten);
        edt_tuoi = findViewById(R.id.edt_tuoi);
        btn_them = findViewById(R.id.btn_them);
        btn_sua = findViewById(R.id.btn_sua);

        //hien thi
        sinhVienAdapter = new SinhVienAdapter();
        lv_sv.setAdapter(sinhVienAdapter);

        // xoa
        lv_sv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Xác nhận xóa ?");
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sinhVienAdapter.XoaSV(position);
                        XoaDuLieuNhap();
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

        //Click item listview do du lieu len edit text
        lv_sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_sua.setEnabled(true);
                btn_them.setEnabled(false);
                vi_tri = position;
                SinhVien sv = (SinhVien) sinhVienAdapter.getItem(position);
                edt_ten.setText(sv.getTen());
                edt_tuoi.setText(sv.getTuoi() + "");
            }
        });

        btn_sua.setEnabled(false);

        btn_them.setOnClickListener(this);
        btn_sua.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_them) {
            // them
            String ten = edt_ten.getText().toString();
            if (ten.isEmpty()) {
                edt_ten.setError("Không được để trống");
                return;
            }
            if (edt_tuoi.getText().toString().isEmpty()) {
                edt_tuoi.setError("Không được để trống");
                return;
            } else {
                int tuoi = Integer.parseInt(edt_tuoi.getText().toString());
                if (tuoi < 0) {
                    edt_tuoi.setError("Tuổi không hợp lệ");
                    return;
                } else {
                    sinhVienAdapter.ThemSV(new SinhVien(ten, tuoi));
                    XoaDuLieuNhap();
                }
            }
        } else {
            // sua
            String ten = edt_ten.getText().toString();
            if (ten.isEmpty()) {
                edt_ten.setError("Không được để trống");
                return;
            }
            if (edt_tuoi.getText().toString().isEmpty()) {
                edt_tuoi.setError("Không được để trống");
                return;
            } else {
                int tuoi = Integer.parseInt(edt_tuoi.getText().toString());
                if (tuoi < 0) {
                    edt_tuoi.setError("Tuổi không hợp lệ");
                    return;
                } else {
                    sinhVienAdapter.SuaSV(vi_tri, new SinhVien(ten, tuoi));
                    XoaDuLieuNhap();
                    btn_sua.setEnabled(false);
                    btn_them.setEnabled(true);
                }
            }
        }
    }

    private void XoaDuLieuNhap() {
        edt_ten.setText("");
        edt_tuoi.setText("");
        edt_ten.requestFocus();
    }
}
