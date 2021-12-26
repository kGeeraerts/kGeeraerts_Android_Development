package be.ehb.androidproject;

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
import be.ehb.androidproject.entities.relations.UserMemeLikes;

public class memeRecyclerAdapter extends RecyclerView.Adapter<memeRecyclerAdapter.MyViewHolder>{

    private List<Meme> list;
    private int uid;

    public memeRecyclerAdapter(List<Meme> list, int uid) {
        this.uid = uid;
        this.list = list;
    }


    @NonNull
    @Override
    public memeRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.meme_layout, parent, false);
        memeRecyclerAdapter.MyViewHolder myViewHolder = new memeRecyclerAdapter.MyViewHolder(layout);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull memeRecyclerAdapter.MyViewHolder holder, int position) {
        TextView title = (TextView) holder.VersionName.findViewById(R.id.memeTitle);
        title.setText(list.get(position).title);

        ImageView img = (ImageView) holder.VersionName.findViewById(R.id.memeImage);
        Bitmap image = BitmapFactory.decodeByteArray(list.get(position).image, 0, list.get(position).image.length);
        img.setImageBitmap(image);

        holder.meme = list.get(position);
        holder.uid = uid;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        Meme meme;
        int uid;
        ConstraintLayout VersionName;
        public MyViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            VersionName = itemView;

            itemView.findViewById(R.id.memeLike).setOnClickListener((new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    UserMemeLikes uml = new UserMemeLikes();
                    uml.memeid = meme.getMemeid();
                    uml.uid = uid;
                    Database db = Database.getInstance(view.getContext());
                    db.memeDao().insertUserMemeLikes(uml);
                    System.out.println("Meme with id " + meme.memeid + " was liked by user with id = " + uid);
                }
            }));
            itemView.findViewById(R.id.memeComments).setOnClickListener((new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    System.out.println("Go to Meme Comments");
                    int memeid = meme.getMemeid();
                    Bundle bundle = new Bundle();
                    bundle.putInt("memeid", memeid);
                    Navigation.findNavController(view).navigate(R.id.action_memeFragment_to_memeCommentsFragment, bundle);
                }
            }));

        }
    }
}
