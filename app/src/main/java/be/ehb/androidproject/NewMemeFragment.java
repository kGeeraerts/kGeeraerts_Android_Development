package be.ehb.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import be.ehb.androidproject.entities.Meme;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewMemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewMemeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewMemeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewMemeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewMemeFragment newInstance(String param1, String param2) {
        NewMemeFragment fragment = new NewMemeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_meme, container, false);
        activity = (MainActivity) getActivity();

        SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
        int uid = sharedPref.getInt("uid", -1);

        ImageView meme = (ImageView) view.findViewById(R.id.newMemeImageDisplay);
        meme.setImageBitmap(activity.newMemeImage);

        view.findViewById(R.id.cancelsavememe).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        activity.newMemeImage = null;
                        Navigation.findNavController(view).navigate(R.id.action_newMemeFragment_to_ownMemeFragment);
                    }
                });

        view.findViewById(R.id.savememe).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        EditText titleText = (EditText) getView().findViewById(R.id.newMemetitleInput);
                        String title = titleText.getText().toString();

                        TextView error = (TextView) getView().findViewById(R.id.newMemeErrorMessage);

                        Database db = Database.getInstance(view.getContext());

                        if(title.matches("")){
                            error.setText("The meme needs a title!");
                        }else if(activity.newMemeImage == null){
                            error.setText("Please select a meme to upload!");
                        }else{
                            Bitmap img = activity.newMemeImage;
                            activity.newMemeImage = null;
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            img.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();

                            Meme newMeme = new Meme(title, byteArray, uid);

                            db.memeDao().insertMeme(newMeme);
                            Navigation.findNavController(view).navigate(R.id.action_newMemeFragment_to_ownMemeFragment);
                        }
                    }
                });

        view.findViewById(R.id.newMemePictureInput).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Intent gallery = new Intent();
                        gallery.setType("image/*");
                        gallery.setAction(Intent.ACTION_GET_CONTENT);

                        getActivity().startActivityForResult(Intent.createChooser(gallery, "Select Image"), 1);
                        System.out.println(activity.newMemeImage);
                    }
                });

        view.findViewById(R.id.reloadNewMeme).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        ImageView img = getView().findViewById(R.id.newMemeImageDisplay);
                        img.setImageBitmap(activity.newMemeImage);

                        System.out.println("Image displayed");
                    }
                });

        return view;
    }

}