package com.example.inos.echome.presenters.suggestion;

import com.example.inos.echome.models.User;
import com.example.inos.echome.models.Users;
import com.example.inos.echome.network.suggestion.ISuggestionNetwork;
import com.example.inos.echome.network.suggestion.SuggestionNetwork;
import com.example.inos.echome.ui.suggestion.ISuggestionActivity;
import com.example.inos.echome.ui.suggestion.SuggestionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azhng on 2018-01-21.
 */

public class SuggestionPresenter implements ISuggestionPresenter {

    private List<User> mUsers;
    private ISuggestionActivity mSuggestionFeedView;
    private ISuggestionNetwork mSuggestionNetwork;

    public SuggestionPresenter(ISuggestionActivity view) {
        mUsers = new ArrayList<>(10);
        mSuggestionFeedView = view;
        mSuggestionNetwork = new SuggestionNetwork(this);
        mSuggestionNetwork.getUsers();
    }

    @Override
    public void notifyUsersRecieved(Users users) {
        mSuggestionFeedView.setUsers(users);
    }

}
