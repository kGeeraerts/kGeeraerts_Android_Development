package be.ehb.androidproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import be.ehb.androidproject.entities.Comment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link memeCommentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class memeCommentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public memeCommentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment memeCommentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static memeCommentsFragment newInstance(String param1, String param2) {
        memeCommentsFragment fragment = new memeCommentsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public int memeid = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            memeid = getArguments().getInt("memeid");
        }
    }


    public MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meme_comments, container, false);
        System.out.println("Comments opened");
        System.out.println(memeid);

        activity = (MainActivity) getActivity();

        Database db = Database.getInstance(view.getContext());
        SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
        int uid = sharedPref.getInt("uid", -1);
        List<Comment> comments = db.memeDao().getMemeWithComments(memeid).get(0).comments;

        RecyclerView recycle  = (RecyclerView) view.findViewById(R.id.memeCommentsRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recycle.setLayoutManager(layoutManager);
        commentRecyclerAdapter adapter = new commentRecyclerAdapter(comments);
        recycle.setAdapter(adapter);

        view.findViewById(R.id.addNewComment).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        EditText newComment = (EditText) getView().findViewById(R.id.newCommentInput);
                        String comment = newComment.getText().toString();

                        if(comment.matches("")){
                        }else {
                            Comment com = new Comment(comment, memeid, uid);
                            db.commentDao().insertComment(com);
                            activity.reloadComments(memeid);
                        }
                    }
                });

        return view;
    }
}