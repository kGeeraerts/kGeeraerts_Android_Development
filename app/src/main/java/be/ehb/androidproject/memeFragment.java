package be.ehb.androidproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.ehb.androidproject.entities.Meme;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link memeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class memeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public memeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment memeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static memeFragment newInstance(String param1, String param2) {
        memeFragment fragment = new memeFragment();
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
        View view = inflater.inflate(R.layout.fragment_meme, container, false);

        Database db = Database.getInstance(view.getContext());
        SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
        int uid = sharedPref.getInt("uid", -1);
        List<Meme> memes = db.memeDao().getAllMemes();

        RecyclerView recycle  = (RecyclerView) view.findViewById(R.id.memeRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycle.setLayoutManager(layoutManager);
        memeRecyclerAdapter adapter = new memeRecyclerAdapter(memes, uid);
        recycle.setAdapter(adapter);

        return view;
    }
}