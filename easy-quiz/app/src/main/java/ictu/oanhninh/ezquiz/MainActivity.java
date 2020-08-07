package ictu.oanhninh.ezquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_SCORE = "keyScore";

    private int last_score;

    private TextView tv_title, tv_score; // khai báo text view
    private ImageButton im_button_play; // khai báo button chơi
    private Spinner spinner_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED); // khoá xoay ngang

        initView(); // ánh xạ các view
        setFont(); // custom font
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title); // text view logo tên game Easy Quiz

        tv_score = findViewById(R.id.tv_score); // text view điểm cao
        loadScore();

        im_button_play = findViewById(R.id.im_button_play); // image button chơi (play)
        im_button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEasyQuiz();
            }
        });

        spinner_difficulty = findViewById(R.id.spinner_difficulty);
        setSpinnerDifficulty();

    }

    private void startEasyQuiz() {
        String difficulty = spinner_difficulty.getSelectedItem().toString();
        // chuyển sang màn hình chơi game
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    private void setSpinnerDifficulty(){
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_difficulty.setAdapter(adapterDifficulty);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(GameActivity.EXTRA_SCORE, 0);
                Log.d("Main", "Home Activity: " + score);
                if (score > last_score) {
                    updateLastScore(score);
                }
            }
        }
    }

    private void loadScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        last_score = prefs.getInt(KEY_SCORE,0);
        tv_score.setText("Điểm cao: " + last_score);
    }

    private void updateLastScore(int score_new) {
        last_score = score_new;
        tv_score.setText("Điểm cao: " + last_score);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SCORE, last_score);
        editor.apply();
    }

    private void setFont() {
        // custom font
        Typeface title = ResourcesCompat.getFont(MainActivity.this, R.font.mijas);
        Typeface mLight = ResourcesCompat.getFont(MainActivity.this, R.font.ml);

        tv_title.setTypeface(title); // custom font text view tên game
        tv_score.setTypeface(mLight); // custom font text view điểm cao
    }
}
