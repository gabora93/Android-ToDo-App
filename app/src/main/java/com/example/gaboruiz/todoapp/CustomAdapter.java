package com.example.gaboruiz.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gaboruiz.todoapp.Class.item;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Gabo Ruiz on 13/12/2018.
 */

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private List<item> lstOfItems;

    public CustomAdapter(Context mContext, List<item> lstOfItems) {
        this.mContext = mContext;
        this.lstOfItems = lstOfItems;
    }

    @Override
    public int getCount() {
        return lstOfItems.size();
    }

    @Override
    public Object getItem(int position) {
        return lstOfItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row,null);

        TextView txtItem = (TextView)view.findViewById(R.id.txtItem);
        txtItem.setText(lstOfItems.get(position).getItem());

        return view;
    }
}
