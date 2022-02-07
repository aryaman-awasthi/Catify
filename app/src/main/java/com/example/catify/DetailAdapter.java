package com.example.catify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.PostViewHolder> {

    List<DataModel> dataList;
    Context context;

    private OnIteClickListener mListener;
    public interface OnIteClickListener {
        void  onItemClick(int position);
    }
    public void setOnItemClickListener(OnIteClickListener listener){
        mListener =  listener;
    }

    public DetailAdapter(Context context, List<DataModel> details){
        this.context = context;
        dataList = details;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catbreedshow, parent, false);
        return  new PostViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        DataModel dataModel = dataList.get(position);
        holder.serialNumber.setText(position+1+"");
        holder.name.setText(dataModel.getName());
        try {
            String url = dataModel.getImage().getUrl();

            Picasso.get()
                    .load(url)
                    .resize(500, 500)
                    .centerCrop()
                    .into(holder.profilePic);
        }catch (Exception e)
        {
            holder.profilePic.setImageResource(R.drawable.logo);
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView serialNumber, name;
        ImageView profilePic;

        public PostViewHolder(@NonNull View itemView, OnIteClickListener listener) {
            super(itemView);
            serialNumber = itemView.findViewById(R.id.serialNumber);
            name = itemView.findViewById(R.id.name);
            profilePic = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
