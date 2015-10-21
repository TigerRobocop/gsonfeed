package com.unibratec.livia.gsonfeed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Livia on 18/10/2015.
 */
public class UserListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    List<User> mListUsers;
    ArrayAdapter<User> mAdapter;
    ProgressDialog mProgress;

    SwipeRefreshLayout mSwipe;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_user, null);
        mSwipe = (SwipeRefreshLayout) v.findViewById(R.id.refreesh);
        mSwipe.setOnRefreshListener(this);
        return v;
    }


    //remover instancias de arraylist e trocar eplo custom adapter, no layout de item botar o custom layout e pa
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListUsers = new ArrayList<User>();
        mAdapter = new ArrayAdapter<User>(getActivity(), android.R.layout.simple_list_item_1, mListUsers);
        setListAdapter(mAdapter);

        LoadList();
    }


    private void LoadList() {

        ConnectivityManager ccnnMrg = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = ccnnMrg.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            UsersTask executeT = new UsersTask();
            executeT.execute();
        } else {
            Toast.makeText(getActivity(), "No connection available", Toast.LENGTH_SHORT).show();
            mSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Activity activity = getActivity();
        if (activity instanceof OnUserClick) {
            User planet = (User) l.getItemAtPosition(position);
            ((OnUserClick) activity).userClick(planet);
        }
    }

    @Override
    public void onRefresh() {
        LoadList();
    }

    public interface OnUserClick {
        void userClick(User user);
    }


    private List<User> loadUsers() {

        List<User> retorno = new ArrayList<User>();

        retorno.add(new User("1",
                "User 1",
                "user1"));
        retorno.add(new User("2",
                "User 2",
                "user2"));

        retorno.add(new User("3",
                "User 3",
                "user3"));

        retorno.add(new User("4",
                "User 4",
                "user4"));

        retorno.add(new User("5",
                "User 5",
                "user5"));

        return retorno;
    }


    private class UsersTask extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);

            mListUsers.addAll(users);
            mAdapter.notifyDataSetChanged();

            mProgress.dismiss();

            mSwipe.setRefreshing(false);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress = ProgressDialog.show(getActivity(), "Wait for it", "Loading...", true);
            mProgress.setCancelable(false);

        }

        @Override
        protected List<User> doInBackground(Void... params) {
            List<User> result = new ArrayList<User>();

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://jsonplaceholder.typicode.com/users")
                        .build();

                // Read the stream

                try {
                    Response response = client.newCall(request).execute();

                    String jsonString = response.body().string();

                    Gson gson = new Gson();

                    result = Arrays.asList(gson.fromJson(jsonString, User[].class));

                } catch (Throwable e) {  //ao invés de IOException - classe parent de exception que abrange erros + exceptions
                    //para pegar erros no json string response por exemplo
                    e.printStackTrace();
                }

                return result;
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }
    }

}
