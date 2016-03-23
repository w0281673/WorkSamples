package com.nscc.w0281673.vidplayer;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Isaac on 2015-12-14.
 */
public class VidListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> name, desc, thumbnail;
    private final ArrayList<Integer> rating;

    public VidListAdapter(Activity context, ArrayList<String> name, ArrayList<String> desc, ArrayList<String> thumbnail, ArrayList<Integer> rating)
    {
        super(context, R.layout.list_view_layout, name);

        this.context=context;
        this.name=name;
        this.desc=desc;
        this.thumbnail=thumbnail;
        this.rating=rating;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_layout, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.movie_name);
        ImageView thbNail = (ImageView) rowView.findViewById(R.id.icon);
        TextView txtDesc = (TextView) rowView.findViewById(R.id.description);

        if(name.get(position).toString().equals("Add New Trailer"))
            txtName.setText(name.get(position));
        else
            txtName.setText(name.get(position) + " " + String.valueOf(rating.get(position)) + "/5");
        int id = context.getResources().getIdentifier(thumbnail.get(position), "drawable", context.getPackageName());
        thbNail.setImageResource(context.getResources().getIdentifier(thumbnail.get(position), "drawable", context.getPackageName()));
        txtDesc.setText(desc.get(position));
        return rowView;
    }
}
