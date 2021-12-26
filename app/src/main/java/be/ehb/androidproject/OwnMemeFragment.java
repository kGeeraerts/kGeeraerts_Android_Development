package be.ehb.androidproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.ehb.androidproject.entities.Meme;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OwnMemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OwnMemeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    public OwnMemeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OwnMemeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OwnMemeFragment newInstance(String param1, String param2) {
        OwnMemeFragment fragment = new OwnMemeFragment();
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_own_meme, container, false);


        Database db = Database.getInstance(view.getContext());
        SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
        int uid = sharedPref.getInt("uid", -1);
        List<Meme> ownMemes = db.userDao().getUserWithMemes(uid).get(0).memes;

        RecyclerView recycle  = (RecyclerView) view.findViewById(R.id.ownMemeRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycle.setLayoutManager(layoutManager);
        OwnMemeRecyclerAdapter adapter = new OwnMemeRecyclerAdapter(ownMemes);
        recycle.setAdapter(adapter);

        view.findViewById(R.id.newmeme).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_ownMemeFragment3_to_newMemeFragment);
                    }
                });




        return view;
    }
}