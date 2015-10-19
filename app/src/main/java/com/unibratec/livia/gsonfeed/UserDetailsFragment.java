package com.unibratec.livia.gsonfeed;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class UserDetailsFragment extends Fragment {

    public static final String TAG_DETAILS = "tag_details";
    public static final String EXTRA_USER = "user";

    User mUser;

    TextView mTxtName;
    TextView mTxtId;
    TextView mTxtUsername;

    public static UserDetailsFragment newInstance(User user) {

        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(EXTRA_USER);
            setHasOptionsMenu(true);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.detail_favorite, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite){
            Toast.makeText(getActivity(), "Add favorite", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_user_details, container, false);

        mTxtName = (TextView)layout.findViewById(R.id.textView_name);
        mTxtId = (TextView)layout.findViewById(R.id.textView_userid);
        mTxtUsername = (TextView) layout.findViewById(R.id.textView_username);

        if (mUser != null){
            mTxtName.setText(mUser.name);
            mTxtId.setText(mUser.id);
            mTxtUsername.setText(mUser.username);
        }

        return layout;
    }

}
