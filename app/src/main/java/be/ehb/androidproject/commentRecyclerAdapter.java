package be.ehb.androidproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import be.ehb.androidproject.entities.Comment;
import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;
import be.ehb.androidproject.entities.relations.UserMemeLikes;

public class commentRecyclerAdapter extends RecyclerView.Adapter<commentRecyclerAdapter.MyViewHolder> {

    private List<Comment> comments;

    public commentRecyclerAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public commentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
        commentRecyclerAdapter.MyViewHolder myViewHolder = new commentRecyclerAdapter.MyViewHolder(layout);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Database db = Database.getInstance(holder.VersionName.getContext());

        TextView owner = (TextView) holder.VersionName.findViewById(R.id.memeCommentOwner);
        User user = db.userDao().getUser(comments.get(position).getUser());
        owner.setText(Integer.toString(position + 1) + ". Comment by " + user.getUserName());

        TextView content = (TextView) holder.VersionName.findViewById(R.id.memeCommentContent);
        content.setText(comments.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout VersionName;
        public MyViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            VersionName = itemView;


        }
    }
}
