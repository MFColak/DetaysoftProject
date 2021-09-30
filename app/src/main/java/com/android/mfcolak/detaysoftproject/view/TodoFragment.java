package com.android.mfcolak.detaysoftproject.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mfcolak.detaysoftproject.R;
import com.android.mfcolak.detaysoftproject.adapter.ToDosListAdapter;
import com.android.mfcolak.detaysoftproject.model.listener.TodoItemClickListener;
import com.android.mfcolak.detaysoftproject.viewmodel.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TodoFragment extends Fragment implements TodoItemClickListener {

    private ToDoViewModel viewModel;
    private ToDosListAdapter toDosListAdapter = new ToDosListAdapter(new ArrayList<>(), this::onToDoClicked);

    @BindView(R.id.todosList)
    RecyclerView todosList;

    @BindView(R.id.todosError)
    TextView todosError;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.refleshLayout)
    SwipeRefreshLayout refreshLayout;

    public TodoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        viewModel.reflesh();

        todosList.setLayoutManager(new LinearLayoutManager(getContext()));
        todosList.setAdapter(toDosListAdapter);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.todos.observe(this, todos -> {
            if (todos != null && todos instanceof List) {
                todosList.setVisibility(View.VISIBLE);
                toDosListAdapter.updateTodosList(todos);
            }
        });
        viewModel.todoLoadError.observe(this, isError -> {
            if (isError != null && isError instanceof Boolean) {
                todosError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }

        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    todosError.setVisibility(View.GONE);
                    todosList.setVisibility(View.GONE);
                }
            }

        });
    }

    @Override
    public void onToDoClicked(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}