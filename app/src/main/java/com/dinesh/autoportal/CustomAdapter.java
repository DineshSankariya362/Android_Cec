package com.dinesh.autoportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList name, email, dob;

    CustomAdapter(Activity activity, Context context, ArrayList name, ArrayList email, ArrayList dob){
        this.activity = activity;
        this.context = context;
        this.name = name;
        this.email = email;
        this.dob= dob;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.textView.setText(String.valueOf(name.get(position)));
        holder.textView2.setText(String.valueOf(email.get(position)));
        holder.textView3.setText(String.valueOf(dob.get(position)));

        //Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(book_id.get(position)));
//                intent.putExtra("title", String.valueOf(book_title.get(position)));
//                intent.putExtra("author", String.valueOf(book_author.get(position)));
//                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView2,textView3;



        MyViewHolder(@NonNull View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3= itemView.findViewById(R.id.textView3);

        }

    }

}

//hey done?