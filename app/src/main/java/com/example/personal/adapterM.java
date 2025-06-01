package com.example.personal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class adapterM extends ArrayAdapter<BeanM> {
    private Context contextM;
    private ArrayList<BeanM> acting;

    private int jam;

    public adapterM( Context context,  int jam, ArrayList<BeanM> acting) {
        super(context, jam,acting);
        this.contextM = context;

        this.jam = jam;
        this.acting = acting;
    }

    public Context getContextM() {
        return contextM;
    }

    public void setContextM(Context contextM) {
        this.contextM = contextM;
    }

    public ArrayList<BeanM> getActing() {
        return acting;
    }

    public void setActing(ArrayList<BeanM> acting) {
        this.acting = acting;
    }

    public int getJam() {
        return jam;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    static class Mask{
        TextView reg1;
        TextView reg2;
        TextView reg3;


    }

    public View getView(int position, View convert, ViewGroup parent) {
        BeanM bean = getItem(position);
        Mask holder;
        if (convert == null) {
            LayoutInflater inflater = LayoutInflater.from(contextM);
            convert = inflater.inflate(jam, parent, false);
            holder = new Mask();
            holder.reg1 = convert.findViewById(R.id.Reg1);
            holder.reg2 = convert.findViewById(R.id.Reg2);
            holder.reg3 = convert.findViewById(R.id.Reg3);
            convert.setTag(holder);

        } else {
            holder = (Mask) convert.getTag();
        }
        if(bean!=null){
            holder.reg1.setText(bean.getReg1());
            holder.reg2.setText(bean.getReg2());
            holder.reg3.setText(bean.getReg3());
        }

        return convert;


    }}

