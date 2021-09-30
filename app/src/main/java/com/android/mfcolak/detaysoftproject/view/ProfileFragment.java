package com.android.mfcolak.detaysoftproject.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mfcolak.detaysoftproject.R;
import com.android.mfcolak.detaysoftproject.adapter.ToDosListAdapter;
import com.android.mfcolak.detaysoftproject.databinding.FragmentProfileBinding;
import com.android.mfcolak.detaysoftproject.util.Util;
import com.android.mfcolak.detaysoftproject.viewmodel.ProfileViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;

    @BindView(R.id.imagePerson)
    ImageView imageView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.surname)
    TextView surname;
    @BindView(R.id.email)
    TextView email;

    public FragmentProfileBinding fragmentProfileBinding;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        viewModel.fetch();


        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.profilLiveData.observe(this, userResponse -> {
            name.setText(userResponse.getResults().get(0).getName().getFirst());
            surname.setText(userResponse.getResults().get(0).getName().getLast());
            email.setText(userResponse.getResults().get(0).getEmail());
            Util.loadImage(imageView, userResponse.getResults().get(0).getPicture().getThumbnail());
        });
    }
}