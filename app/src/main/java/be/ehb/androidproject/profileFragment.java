package be.ehb.androidproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import be.ehb.androidproject.entities.Meme;
import be.ehb.androidproject.entities.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {
        profileFragment fragment = new profileFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
        int uid = sharedPref.getInt("uid", -1);
        System.out.println(uid);
        Database db = Database.getInstance(view.getContext());
        User user = db.userDao().getUser(uid);
        List<Meme> memes = db.userDao().getUserWithMemes(user.getUid()).get(0).memes;
        List<Meme> likes = db.userDao().getUserWithLikes(user.getUid()).get(0).memes;

        TextView username = view.findViewById(R.id.profileUsername);
        username.setText(user.getUserName());
        TextView memeAmount = view.findViewById(R.id.profileMemeAmount);
        memeAmount.setText(String.valueOf(memes.size()));
        TextView likeAmount = view.findViewById(R.id.profileLikesAmount);
        likeAmount.setText(String.valueOf(likes.size()));


        view.findViewById(R.id.edit).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_editProfileFragment);
                    }
                });

        view.findViewById(R.id.own).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_ownMemeFragment);
                    }
                });

        view.findViewById(R.id.likedmemes).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_likedMemesFragment);
                    }
                });

        view.findViewById(R.id.comments).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_commentsFragment);
                    }
                });

        view.findViewById(R.id.logout).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        getActivity().finish();
                    }
                });

        return view;
    }
}