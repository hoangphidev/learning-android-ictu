package tech.hoangphi.mycustomlistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends BaseAdapter {
    private ArrayList<SinhVien> mangSV;

    public SinhVienAdapter() {
        this.mangSV = new ArrayList<>();
        this.mangSV.add(new SinhVien("Phi", 22));
        this.mangSV.add(new SinhVien("Oanh", 21));
        this.mangSV.add(new SinhVien("Bac", 24));
    }

    @Override
    public int getCount() {
        return mangSV.size();
    }

    @Override
    public Object getItem(int position) {
        return mangSV.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sinh_vien, parent, false);

        TextView tv_ten = convertView.findViewById(R.id.tv_ten);
        TextView tv_tuoi = convertView.findViewById(R.id.tv_tuoi);

        SinhVien sv = (SinhVien) getItem(position);
        tv_ten.setText(sv.getTen());
        tv_tuoi.setText(sv.getTuoi()+"");

        return convertView;
    }

    public void ThemSV(SinhVien sv){
        this.mangSV.add(sv);
        notifyDataSetChanged();
    }

    public void SuaSV(int vi_tri, SinhVien sv){
        this.mangSV.set(vi_tri, sv);
        notifyDataSetChanged();
    }

    public void XoaSV(int vi_tri){
        this.mangSV.remove(vi_tri);
        notifyDataSetChanged();
    }
}
