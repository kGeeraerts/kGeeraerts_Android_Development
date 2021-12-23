package be.ehb.androidproject;

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
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        view.findViewById(R.id.toLogin).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                    }
                });

        view.findViewById(R.id.register).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        EditText username = (EditText) getView().findViewById(R.id.registerUsernameInput);
                        String name = username.getText().toString();
                        EditText password = (EditText) getView().findViewById(R.id.registerPasswordInput);
                        String pw = password.getText().toString();
                        EditText passwordcheck = (EditText) getView().findViewById(R.id.registerPasswordCheckInput);
                        String pwcheck = passwordcheck.getText().toString();

                        Database db = Database.getInstance(view.getContext());

                        TextView error = (TextView) getView().findViewById(R.id.registerError);

                        if(name.matches("") || pw.matches("") || pwcheck.matches("")){
                            error.setText("All fields must be entered!");
                        }else if(!pw.matches(pwcheck)){
                            error.setText("Both passwords fields must be same!");
                        }else if(db.userDao().getUser(name) != null){
                            error.setText("This username is already used!");
                        }else{
                            User newUser = new User(name, BCrypt.hashpw(pw, BCrypt.gensalt()));
                            db.userDao().insertUser(newUser);
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                        }
                    }
                });

        return view;
    }
}