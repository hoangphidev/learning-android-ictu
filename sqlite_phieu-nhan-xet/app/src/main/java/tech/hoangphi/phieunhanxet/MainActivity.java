package tech.hoangphi.phieunhanxet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_ten, edt_chưc_vu;
    private RadioButton rd_tot, rd_kha, rd_tb, rd_yeu;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        // anh xa
        edt_ten = findViewById(R.id.edt_ten);
        edt_chưc_vu = findViewById(R.id.edt_cv);
        rd_tot = findViewById(R.id.rd_tot);
        rd_kha = findViewById(R.id.rd_kha);
        rd_tb = findViewById(R.id.rd_tb);
        rd_yeu = findViewById(R.id.rd_yeu);
        // bat su kien cac nut
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_view).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                String ten = "";
                String chucvu = "";
                String nhanxet = "";
                if (edt_ten.getText().toString().isEmpty()) {
                    edt_ten.setError("Không được để trống");
                    return;
                } else {
                    ten = edt_ten.getText().toString();
                }
                if (edt_chưc_vu.getText().toString().isEmpty()) {
                    edt_chưc_vu.setError("Không được để trống");
                    return;
                } else {
                    chucvu = edt_chưc_vu.getText().toString();
                }

                if (rd_tot.isChecked() == true) {
                    nhanxet = rd_tot.getText().toString();
                }
                if (rd_kha.isChecked() == true) {
                    nhanxet = rd_kha.getText().toString();
                }
                if (rd_tb.isChecked() == true) {
                    nhanxet = rd_tb.getText().toString();
                }
                if (rd_yeu.isChecked() == true) {
                    nhanxet = rd_yeu.getText().toString();
                }
                db.themPhieu(new Phieu(ten, chucvu, nhanxet));
                xoaDuLieuNhap();
                Toast.makeText(this, "Thêm thành công\t" + ten + "\t" + chucvu + "\t" + nhanxet, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                if (db.mangPhieu().size() > 0) {
                    db.xoaPhieu();
                    xoaDuLieuNhap();
                    Toast.makeText(this, "Xóa thành công toàn bộ dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Không có dữ liệu để xóa", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_view:
                if (db.mangPhieu().size() > 0) {
                    Phieu phieu = db.layPhieu();
                    xoaDuLieuNhap();
                    Toast.makeText(this, "Phiếu nhận xét vừa lưu là\n " + phieu.getTen() + " - " + phieu.getChucVu() + " - " + phieu.getNhanXet(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Thêm phiếu trước khi xem", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void xoaDuLieuNhap() {
        edt_ten.setText("");
        edt_ten.requestFocus();
        edt_chưc_vu.setText("");
        rd_tot.setChecked(true);
        rd_kha.setChecked(false);
        rd_tb.setChecked(false);
        rd_yeu.setChecked(false);
    }
}
