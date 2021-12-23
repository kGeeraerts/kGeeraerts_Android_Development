package be.ehb.androidproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.mindrot.jbcrypt.BCrypt;

import be.ehb.androidproject.entities.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
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
        View view =  inflater.inflate(R.layout.fragment_edit_profile, container, false);

        view.findViewById(R.id.cancel).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_profileFragment);
                    }
                });

        view.findViewById(R.id.save).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        EditText oldpassword = (EditText) getView().findViewById(R.id.editProfileOldPasswordInput);
                        String oldpw = oldpassword.getText().toString();
                        EditText password = (EditText) getView().findViewById(R.id.editProfileNewPasswordInput);
                        String pw = password.getText().toString();
                        EditText passwordcheck = (EditText) getView().findViewById(R.id.editProfileNewPasswordCheckInput);
                        String pwcheck = passwordcheck.getText().toString();

                        SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
                        int uid = sharedPref.getInt("uid", -1);

                        Database db = Database.getInstance(view.getContext());
                        User user = db.userDao().getUser(uid);

                        TextView error = (TextView) getView().findViewById(R.id.editProfileError);

                        if(oldpw.matches("") || pw.matches("") || pwcheck.matches("")){
                            error.setText("All fields must be entered!");
                        }else if(!pw.matches(pwcheck)) {
                            error.setText("Both passwords fields must be same!");
                        }else if(!BCrypt.checkpw(oldpw, user.getPassword())){
                            error.setText("Old password is wrong!");
                        }else{
                            db.userDao().updatepw(BCrypt.hashpw(pw, BCrypt.gensalt()), user.getUid());
                            System.out.println("Password updated!");
                            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_profileFragment);
                        }


                    }
                });

        return view;
    }
}