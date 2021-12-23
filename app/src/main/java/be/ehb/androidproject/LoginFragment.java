package be.ehb.androidproject;

import android.content.Intent;
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
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        view.findViewById(R.id.toRegister).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
                    }
                });

        view.findViewById(R.id.login).setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view){
                        EditText username = (EditText) getView().findViewById(R.id.loginUsernameInput);
                        String name = username.getText().toString();
                        EditText password = (EditText) getView().findViewById(R.id.loginPasswordInput);
                        String pw = password.getText().toString();

                        Database db = Database.getInstance(view.getContext());
                        User user = db.userDao().getUser(name);

                        TextView error = (TextView) getView().findViewById(R.id.loginError);

                        if(user == null){
                            error.setText("Username is not registered, username is case-sensitive!");
                        }else if(BCrypt.checkpw(pw, user.getPassword())){
                            error.setText("");
                            SharedPreferences sharedPref = getActivity().getSharedPreferences("appPref", view.getContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("uid", user.getUid());
                            editor.apply();
                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            error.setText("Wrong password!");
                        }
                    }
                });



        return view;
    }
}