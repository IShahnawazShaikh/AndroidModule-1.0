package com.shahnawazshaikh.classcontentdetailmodule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahnawazshaikh.classcontentdetailmodule.R;
import com.shahnawazshaikh.classcontentdetailmodule.bean.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter <CourseAdapter.ItemsViewHolder> {
    private Context context;
    private List<Course> list;

    public CourseAdapter(Context context, List<Course> list) {
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.courserecycler,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Course course=list.get(position);
        Uri uri = Uri.parse(course.getVpath());
        holder.vpath.setVideoURI(uri);
        holder.vpath.start();

        holder.vname.setText(course.getVname());
        holder.vid.setText(course.getVid());
        holder.vdesp.setText("Description| "+course.getVdesp());
        holder.vofclass.setText("Class|"+course.getVofclass());
        holder.vdate.setText(course.getVdate());
        holder.vsub.setText("Subject| "+course.getVsub());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView vid,vname,vdesp,vsub,vofclass,vdate;
        VideoView vpath;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            vpath=itemView.findViewById(R.id.vpath);
            vid=itemView.findViewById(R.id.vid);
            vname=itemView.findViewById(R.id.vname);
            vdesp=itemView.findViewById(R.id.vdesp);
            vsub=itemView.findViewById(R.id.vsub);
            vofclass=itemView.findViewById(R.id.vofclass);
            vdate=itemView.findViewById(R.id.vdate);
        }
    }

}
