package com.android.mfcolak.detaysoftproject.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.mfcolak.detaysoftproject.R;
import com.android.mfcolak.detaysoftproject.databinding.FragmentStartBinding;
import com.bumptech.glide.Glide;

public class startFragment extends Fragment {

    public FragmentStartBinding fragmentStartBinding;   // view binding
    ImageView welcomeImage;
    String Url = "";

    public startFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentStartBinding = FragmentStartBinding.inflate(inflater, container, false);
        return fragmentStartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Url = "https://logowik.com/content/uploads/images/letter-v-logo-template1570.jpg";
        welcomeImage = fragmentStartBinding.imagewelcome;
        Glide.with(this).asBitmap().load(Url).into(welcomeImage);


        fragmentStartBinding.mainpageBtn.setOnClickListener(view1 -> {
            onGoToMain();
        });
    }

    void onGoToMain() {
        NavDirections action = startFragmentDirections.actionMain();
        Navigation.findNavController(fragmentStartBinding.mainpageBtn).navigate(action);

    }
}