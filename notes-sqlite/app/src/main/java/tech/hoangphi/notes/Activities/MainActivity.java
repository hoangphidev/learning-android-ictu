package tech.hoangphi.notes.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tech.hoangphi.notes.Database.DBHelper;
import tech.hoangphi.notes.Fragments.HomeFragment;
import tech.hoangphi.notes.Models.Notes;
import tech.hoangphi.notes.R;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn_fab;
    DBHelper mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadFragment();
        mDatabase = new DBHelper(this);
        btn_fab = findViewById(R.id.btn_add);
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotesDialog();
            }
        });
    }

    private void addNotesDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.dialog_add_note, null);

        final EditText edt_body = subView.findViewById(R.id.edt_body);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm ghi chú mới");
        builder.setView(subView);
        builder.create();
        builder.setCancelable(false);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String body = edt_body.getText().toString();
                Notes notes = new Notes(body);
                mDatabase.AddNotes(notes);
                LoadFragment();
            }
        });
        builder.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void LoadFragment() {
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
