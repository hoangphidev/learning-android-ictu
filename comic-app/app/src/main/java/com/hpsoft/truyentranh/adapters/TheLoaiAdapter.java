package com.hpsoft.truyentranh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hpsoft.truyentranh.R;
import com.hpsoft.truyentranh.objects.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TheLoai> theLoaiDataList;

    public TheLoaiAdapter(Context context, int layout, List<TheLoai> theLoaiDataList) {
        this.context = context;
        this.layout = layout;
        this.theLoaiDataList = theLoaiDataList;
    }

    @Override
    public int getCount() {
        return theLoaiDataList.size();
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
        TextView tvNameTheLoai = view.findViewById(R.id.tvNameTheLoai);
        TheLoai theLoai = theLoaiDataList.get(i);
        tvNameTheLoai.setText(theLoai.getTenTheLoai());
        return view;
    }
}
