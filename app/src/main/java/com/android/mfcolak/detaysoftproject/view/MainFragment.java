package com.android.mfcolak.detaysoftproject.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.mfcolak.detaysoftproject.R;
import com.android.mfcolak.detaysoftproject.databinding.FragmentMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainFragment extends Fragment {

    BottomNavigationView bottomNavigationView;
    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView  = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(buttonNavMetod);
        getChildFragmentManager().beginTransaction().replace(R.id.flFragment,new TodoFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener buttonNavMetod = menuItem -> {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.profil:
                fragment = new ProfileFragment();
                break;
            case R.id.todo:
                fragment = new TodoFragment();
                break;

        }
        getChildFragmentManager().beginTransaction().replace(R.id.flFragment,fragment).commit();


        return true;
    };

}
