package tech.hoangphi.democustomlv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NguoiAdapter extends BaseAdapter {
    private ArrayList<Nguoi> arrNguoi;

    public NguoiAdapter() {
        this.arrNguoi = new ArrayList<>();
        this.arrNguoi.add(new Nguoi("Hồ Chí Minh", "Chủ tịch nước Việt Nam", R.drawable.bacho));
        this.arrNguoi.add(new Nguoi("Obama", "Cựu tổng thống Hoa Kỳ", R.drawable.obama));
        this.arrNguoi.add(new Nguoi("Hoàng Phi", "CEO Hoàng Phi Mobile", R.drawable.phi));
    }

    @Override
    public int getCount() {
        return arrNguoi.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_lv, null);

        TextView tv_ten = convertView.findViewById(R.id.tv_ten);
        TextView tv_cv = convertView.findViewById(R.id.tv_cv);
        ImageView imv_avt = convertView.findViewById(R.id.imv_avt);

        Nguoi nguoi = (Nguoi) getItem(position);
        tv_cv.setText(nguoi.getCv());
        tv_ten.setText(nguoi.getTen());
        imv_avt.setImageResource(nguoi.getAnh());

        return convertView;
    }

    public void them(Nguoi nguoi) {
        this.arrNguoi.add(nguoi);
        notifyDataSetChanged();
    }

    public void sua(int vitri, Nguoi nguoi) {
        this.arrNguoi.set(vitri, nguoi);
        notifyDataSetChanged();
    }

    public void xoa(int vi_tri){
        this.arrNguoi.remove(vi_tri);
        notifyDataSetChanged();
    }

}
