package ictu.oanhninh.ezquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    // khai báo các view
    TextView tv_play_score, tv_question_count, tv_time_count_down, tv_question, tv_difficulty;
    Button btn_next_confirm;
    RadioButton radio_button_key_1, radio_button_key_2, radio_button_key_3;
    RadioGroup radio_group_key;

    private ColorStateList textColorDefaultRb; // khai báo màu chữ mặc định của các đáp án
    private ColorStateList textColorDefaultCd; // khai báo màu chữ của thời gian đếm ngược

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private Question currentQuestion;
    private ArrayList<Question> listQuestions;
    private int questionCounter; // câu hỏi hiện tại
    private int questionCountTotal; // tổng số câu hỏi
    private int score; // điểm
    private boolean answered; // trạng thái kiểm tra đã trả lời hay chưa
    private long backPressedTime;
    private String difficulty_level;

    QuizDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED); // khoá xoay ngang
        difficulty_level = getIntent().getStringExtra(MainActivity.EXTRA_DIFFICULTY);
        initView(); // ánh xạ view
        setFont(); // custom font
    }

    private void initView() {
        // Ánh xạ các view
        tv_play_score = findViewById(R.id.tv_play_score); // text view điểm
        tv_question_count = findViewById(R.id.tv_question_count); // text view số câu hỏi
        tv_difficulty = findViewById(R.id.tv_difficulty); // text view độ khó
        tv_time_count_down = findViewById(R.id.tv_time_count_down); // text view thời gian
        tv_question = findViewById(R.id.tv_question); // text view nội dung câu hỏi
        btn_next_confirm = findViewById(R.id.btn_next_confirm); // button câu tiếp theo, xác nhận
        radio_button_key_1 = findViewById(R.id.radio_button_key_1); // radio button đáp án 1
        radio_button_key_2 = findViewById(R.id.radio_button_key_2); // radio button đáp án 2
        radio_button_key_3 = findViewById(R.id.radio_button_key_3); // radio button đáp án 3
        radio_group_key = findViewById(R.id.radio_group_key);

        textColorDefaultRb = radio_button_key_1.getTextColors();
        textColorDefaultCd = tv_time_count_down.getTextColors();

        if (difficulty_level.equals(Question.DIFFICULTY_EASY)){
            tv_difficulty.setText(getResources().getString(R.string.level_easy));
        }else if (difficulty_level.equals(Question.DIFFICULTY_MEDIUM)){
            tv_difficulty.setText(getResources().getString(R.string.level_medium));
        }else if (difficulty_level.equals(Question.DIFFICULTY_HARD)){
            tv_difficulty.setText(getResources().getString(R.string.level_hard));
        }

        dbHelper = new QuizDbHelper(this);
        listQuestions = dbHelper.getQuestion(difficulty_level);
        questionCountTotal = listQuestions.size(); // tổng số câu hỏi dựa vào tổng số phần tử mảng listQuestions
        Collections.shuffle(listQuestions); // xáo trộn câu hỏi

        showNextQuestion(); // hiện nội dung câu hỏi

        btn_next_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (radio_button_key_1.isChecked() || radio_button_key_2.isChecked() || radio_button_key_3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(GameActivity.this, "Bạn chưa chọn đáp án.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        // set màu mặc định cho các đáp án
        radio_button_key_1.setTextColor(textColorDefaultRb);
        radio_button_key_2.setTextColor(textColorDefaultRb);
        radio_button_key_3.setTextColor(textColorDefaultRb);
        radio_group_key.clearCheck(); // bỏ check hết

        if (questionCounter < questionCountTotal) {
            currentQuestion = listQuestions.get(questionCounter); // lấy nội dung câu hỏi hiện tại
            // set dữ liệu lên các vieu
            tv_question.setText(currentQuestion.getQuestion());
            radio_button_key_1.setText(currentQuestion.getOption1());
            radio_button_key_2.setText(currentQuestion.getOption2());
            radio_button_key_3.setText(currentQuestion.getOption3());

            questionCounter++; // tăng lên câu khác
            tv_question_count.setText("Câu hỏi: " + questionCounter + "/" + questionCountTotal); // set số câu trên tổng số
            answered = false; // trạng thái chưa trả lời
            btn_next_confirm.setText("Xác nhận"); // khi mà đến câu cuối cùng nút câu tiếp tục sẽ chuyển sang kết thúc.

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) ((timeLeftInMillis / 1000) / 60);
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(new Locale("vi", "VN"), "%02d:%02d", minutes, seconds);
        tv_time_count_down.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            tv_time_count_down.setTextColor(Color.RED);
        } else {
            tv_time_count_down.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rb_selected = findViewById(radio_group_key.getCheckedRadioButtonId());
        int answerNr = radio_group_key.indexOfChild(rb_selected) + 1;
        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            tv_play_score.setText("Điểm: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        radio_button_key_1.setTextColor(Color.RED);
        radio_button_key_2.setTextColor(Color.RED);
        radio_button_key_3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                radio_button_key_1.setTextColor(Color.GREEN);
                tv_question.setText("A là đáp án chính xác");
                break;
            case 2:
                radio_button_key_2.setTextColor(Color.GREEN);
                tv_question.setText("B là đáp án chính xác");
                break;
            case 3:
                radio_button_key_3.setTextColor(Color.GREEN);
                tv_question.setText("C là đáp án chính xác");
                break;
        }

        if (questionCounter < questionCountTotal) {
            btn_next_confirm.setText("Câu tiếp theo");
        } else {
            btn_next_confirm.setText("Kết thúc");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Bấm quay lại một lần nữa để kết thúc", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void setFont() {
        // custom font
        Typeface mLight = ResourcesCompat.getFont(GameActivity.this, R.font.ml);
        Typeface mMedium = ResourcesCompat.getFont(GameActivity.this, R.font.mm);
        // custom font cho text view
        tv_play_score.setTypeface(mLight);
        tv_question_count.setTypeface(mLight);
        tv_difficulty.setTypeface(mLight);
        tv_time_count_down.setTypeface(mMedium);
        tv_question.setTypeface(mLight);
        // custom font cho radio button đáp án
        radio_button_key_1.setTypeface(mLight);
        radio_button_key_2.setTypeface(mLight);
        radio_button_key_3.setTypeface(mLight);
        // custom font cho button câu tiếp theo, xác nhận
        btn_next_confirm.setTypeface(mMedium);
    }
}
