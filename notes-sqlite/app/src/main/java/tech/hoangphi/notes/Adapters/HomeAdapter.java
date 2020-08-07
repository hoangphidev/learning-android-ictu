package tech.hoangphi.notes.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tech.hoangphi.notes.Activities.MainActivity;
import tech.hoangphi.notes.Database.DBHelper;
import tech.hoangphi.notes.Fragments.HomeFragment;
import tech.hoangphi.notes.Models.Notes;
import tech.hoangphi.notes.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<Notes> listNotes;
    DBHelper mDatabase;

    public HomeAdapter(Context context, List<Notes> listNotes) {
        this.context = context;
        this.listNotes = listNotes;
        mDatabase = new DBHelper(context);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Notes note = listNotes.get(position);
        holder.tv_body.setText(note.getBody());

        holder.imv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xoá ?");
                builder.create();
                builder.setNegativeButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabase.DeleteNotes(note.getId());
                        ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    }
                });

                builder.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        });

        holder.imv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNotes(note);
            }
        });
    }

    private void editNotes(final Notes notes) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.dialog_add_note, null);

        final EditText edt_body = subView.findViewById(R.id.edt_body);

        if (notes != null) {
            edt_body.setText(notes.getBody());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sửa ghi chú");
        builder.setView(subView);
        builder.setCancelable(false);
        builder.create();
        builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String body = edt_body.getText().toString();
                mDatabase.UpdateNotes(new Notes(notes.getId(), body)); // update note
                // refresh fragment
                ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });
        builder.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // set code cancel
            }
        });
        builder.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_body;
        ImageView imv_edit, imv_del;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_body = itemView.findViewById(R.id.tv_body);
            imv_edit = itemView.findViewById(R.id.imv_edit);
            imv_del = itemView.findViewById(R.id.imv_delete);
        }
    }
}
