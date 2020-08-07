package tech.hoangphi.notes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import tech.hoangphi.notes.R;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView imv_logo;
    private WelcomeActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        activity = this;
        imv_logo = findViewById(R.id.imv_logo);
        imv_logo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_in));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imv_logo.setAnimation(AnimationUtils.loadAnimation(activity,R.anim.splash_out));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imv_logo.setVisibility(View.GONE);
                        startActivity(new Intent(activity, MainActivity.class));
                        finish();
                    }
                },1500);
            }
        },2000);
    }
}
