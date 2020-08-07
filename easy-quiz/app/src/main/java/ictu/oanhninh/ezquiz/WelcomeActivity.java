package ictu.oanhninh.ezquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imv_logo; // khai báo ảnh logo
    private TextView tv_copyright; // khai báo text view tên bản quyền tác giả
    private WelcomeActivity activity; // khai báo màn hình hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED); // khoá xoay ngang màn hình

        activity = this; // gán màn hình hiện tại

        // ánh xạ các view
        imv_logo = findViewById(R.id.imv_logo);
        tv_copyright = findViewById(R.id.tv_copyright);

        // custom font
        Typeface MLight = ResourcesCompat.getFont(activity, R.font.mm);
        tv_copyright.setTypeface(MLight);

        // load hiệu ứng cho ảnh logo
        imv_logo.setAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in)); // hiệu ứng vào
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imv_logo.setAnimation(AnimationUtils.loadAnimation(activity,R.anim.splash_out)); // hiệu ứng ra
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imv_logo.setVisibility(View.GONE); // GONE - ẩn 1 view hoàn toàn để các view khác chiếm vị trí nó
                        // sau khi load xong hiệu ứng ảnh, chuyển vào màn hình chính
                        startActivity(new Intent(activity, MainActivity.class));
                        finish();
                    }
                },1500); // ngừng 1.5s
            }
        }, 2000); // ngừng 2s

    }
}
