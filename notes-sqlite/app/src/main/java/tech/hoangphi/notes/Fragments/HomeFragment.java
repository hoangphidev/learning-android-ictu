package tech.hoangphi.notes.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tech.hoangphi.notes.Adapters.HomeAdapter;
import tech.hoangphi.notes.Database.DBHelper;
import tech.hoangphi.notes.Models.Notes;
import tech.hoangphi.notes.R;

public class HomeFragment extends Fragment {
    View view;
    HomeAdapter notesAdapter;
    RecyclerView rcv_notes;
    DBHelper mDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        rcv_notes = view.findViewById(R.id.rcv_notes);
        mDatabase = new DBHelper(getContext());
        List<Notes> listNotes = mDatabase.getAllNotes();

        if (listNotes.size() > 0){
            rcv_notes.setLayoutManager(new LinearLayoutManager(getContext()));
            notesAdapter = new HomeAdapter(getContext(),listNotes);
            rcv_notes.setAdapter(notesAdapter);
        }else {
            Toast.makeText(getContext(), "Khong co du lieu", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
