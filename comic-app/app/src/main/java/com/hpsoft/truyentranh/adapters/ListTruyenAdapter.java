package com.hpsoft.truyentranh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.objects.Truyen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListTruyenAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Truyen> truyenDataList;

    public ListTruyenAdapter(Context context, int layout, List<Truyen> truyenDataList) {
        this.context = context;
        this.layout = layout;
        this.truyenDataList = truyenDataList;
    }

    @Override
    public int getCount() {
        return truyenDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        TextView tenTruyen = view.findViewById(R.id.tenTruyen);
        TextView tvRate = view.findViewById(R.id.tvRating);
        ImageView imgThum = view.findViewById(R.id.ivThum);

        Truyen truyen = truyenDataList.get(i);
        tenTruyen.setText(truyen.getTenTruyen());
        tvRate.setText(truyen.getRating());
        Picasso.get()
                .load(truyen.getLinkImage())
                .resize(80, 110)
                .centerCrop()
                .into(imgThum);

        return view;
    }
}
