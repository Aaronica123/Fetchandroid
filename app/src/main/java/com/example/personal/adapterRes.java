package com.example.personal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class adapterRes extends ArrayAdapter<BeanM3> {
    private Context context1;
    private ArrayList<BeanM3> act;
    private int ger;

    public adapterRes(  Context context1, int ger,ArrayList<BeanM3> act ) {
        super(context1, ger,act);
        this.context1 = context1;
        this.act = act;
        this.ger = ger;
    }

    public Context getContext1() {
        return context1;
    }

    public void setContext1(Context context1) {
        this.context1 = context1;
    }

    public ArrayList<BeanM3> getAct() {
        return act;
    }

    public void setAct(ArrayList<BeanM3> act) {
        this.act = act;
    }

    public int getGer() {
        return ger;
    }

    public void setGer(int ger) {
        this.ger = ger;
    }

    static class fear{
        TextView fname;
        TextView marks;


    }
    public View getView(int position, View convert, ViewGroup parent){
        BeanM3 bean=getItem(position);
        fear take;
        if (convert==null){
            LayoutInflater inflater = LayoutInflater.from(context1);
            convert = inflater.inflate(ger, parent, false);
            take=new fear();

            take.fname=convert.findViewById(R.id.fname);
            take.marks=convert.findViewById(R.id.marks);
            convert.setTag(take);
        }
        else{ take=(fear) convert.getTag();}
        if(bean!=null){
            take.fname.setText(bean.getName());
            take.marks.setText(bean.getMarks());

        }
        return convert;


    }
}
