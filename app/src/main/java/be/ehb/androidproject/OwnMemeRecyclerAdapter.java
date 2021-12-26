package be.ehb.androidproject;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.androidproject.entities.Meme;

public class OwnMemeRecyclerAdapter extends RecyclerView.Adapter<OwnMemeRecyclerAdapter.MyViewHolder>  {

    private List<Meme> list;

    public OwnMemeRecyclerAdapter(List<Meme> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.own_meme_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(layout);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView title = (TextView) holder.VersionName.findViewById(R.id.ownMemeTitle);
        title.setText(list.get(position).title);

        ImageView img = (ImageView) holder.VersionName.findViewById(R.id.ownMemeImage);
        Bitmap image = BitmapFactory.decodeByteArray(list.get(position).image, 0, list.get(position).image.length);
        img.setImageBitmap(image);

        TextView like = (TextView) holder.VersionName.findViewById(R.id.ownMemeLikes);
        Database db = Database.getInstance(holder.VersionName.getContext());
        int amount = db.memeDao().getMemeWithLikes(list.get(position).getMemeid()).get(0).users.size();
        String likes = Integer.toString(amount);
        like.setText("Likes: " + likes);

        holder.meme = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        Meme meme;
        ConstraintLayout VersionName;
        public MyViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            VersionName = itemView;

            itemView.findViewById(R.id.ownMemeRemove).setOnClickListener((new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    System.out.println("Meme with id " + meme.memeid + " was removed!");
                }
            }));
            itemView.findViewById(R.id.ownMemeComments).setOnClickListener((new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    System.out.println("Go to Meme Comments");
                    int memeid = meme.getMemeid();
                    Bundle bundle = new Bundle();
                    bundle.putInt("memeid", memeid);
                    Navigation.findNavController(view).navigate(R.id.action_ownMemeFragment_to_memeCommentsFragment, bundle);
                }
            }));

        }
    }
}
