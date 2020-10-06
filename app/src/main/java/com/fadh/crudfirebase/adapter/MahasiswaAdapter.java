package com.fadh.crudfirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fadh.crudfirebase.R;
import com.fadh.crudfirebase.model.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Mahasiswa> mahasiswaArrayList = new ArrayList<>();

    public void setMahasiswaArrayList(ArrayList<Mahasiswa> mahasiswaArrayList){
        this.mahasiswaArrayList = mahasiswaArrayList;
    }
    public MahasiswaAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return mahasiswaArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return mahasiswaArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null){
            LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        Mahasiswa mahasiswa = (Mahasiswa) getItem(i);
        viewHolder.bind(mahasiswa);
        return itemView;
    }
    private class ViewHolder {
        private TextView txtNim, txtName;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
            txtNim = view.findViewById(R.id.txt_nim);
        }

        void bind(Mahasiswa mahasiswa) {
            txtName.setText(mahasiswa.getNama());
            txtNim.setText(mahasiswa.getNim());
        }
    }
}
