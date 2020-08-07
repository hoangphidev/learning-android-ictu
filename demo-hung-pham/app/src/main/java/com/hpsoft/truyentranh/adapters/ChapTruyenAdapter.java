package com.hpsoft.truyentranh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.objects.ListChap;

import java.util.Collections;
import java.util.List;

public class ChapTruyenAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ListChap> listChaps;
    public ChapTruyenAdapter(Context context, int layout, List<ListChap> listChaps) {
        this.context = context;
        this.layout = layout;
        this.listChaps = listChaps;
        Collections.reverse(this.listChaps);
    }

    @Override
    public int getCount() {
        return listChaps.size();
    }

    @Override
    public Object getItem(int i) {
        return listChaps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        TextView tvTenChap, tvDate, tvLuotXem;
        tvTenChap = view.findViewById(R.id.tvTenChap);
        tvDate = view.findViewById(R.id.tvDate);
        tvLuotXem = view.findViewById(R.id.tvView);
        tvTenChap.setText(listChaps.get(i).getTenChap());
        tvLuotXem.setText(listChaps.get(i).getLuotXem());
        tvDate.setText(listChaps.get(i).getNgayDang());
        return view;
    }
}
