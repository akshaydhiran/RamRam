package com.akshaysingh5.ramram.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akshaysingh5.ramram.MessageActivity;
import com.akshaysingh5.ramram.Model.User;
import com.akshaysingh5.ramram.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHoldar>
{
    private Context mContext;
    private List<User> mUsers;
    private boolean ischat;

    public UserAdapter(Context mContext, List<User> mUsers,boolean ischat) {
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat= ischat;

    }

    @NonNull
    @Override
    public ViewHoldar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHoldar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldar holder, int position) {

       final User user = mUsers.get(position);
        holder.username.setText(user.getUsername());

        if(user.getImageURL().equals("default"))
        {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else
        {
            Glide.with(mContext).load(user.getImageURL()).into(holder.profile_image);
        }

        if(ischat)
        {
            if(user.getStatus().equals("online"))
            {
                holder.img_on.setVisibility(View.VISIBLE);
                holder.img_off.setVisibility(View.GONE);
            }
            else
            {
                holder.img_on.setVisibility(View.GONE);
                holder.img_off.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            holder.img_on.setVisibility(View.GONE);
            holder.img_off.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHoldar extends RecyclerView.ViewHolder
    {
        public TextView username;
        public ImageView profile_image;
        private ImageView img_on;
        private ImageView img_off;

        public ViewHoldar(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            img_on = itemView.findViewById(R.id.img_on);
            img_off = itemView.findViewById(R.id.img_off);
        }
    }

}
