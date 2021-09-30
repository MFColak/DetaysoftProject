package com.android.mfcolak.detaysoftproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mfcolak.detaysoftproject.R;
import com.android.mfcolak.detaysoftproject.model.listener.TodoItemClickListener;
import com.android.mfcolak.detaysoftproject.model.response.TodoResponse;

import java.util.ArrayList;
import java.util.List;

public class ToDosListAdapter extends RecyclerView.Adapter<ToDosListAdapter.TodoViewHolder> {

    private ArrayList<TodoResponse> todos;
    private TodoItemClickListener itemClickListener;

    public ToDosListAdapter(ArrayList<TodoResponse> todosList, TodoItemClickListener itemClickListener) {
        this.todos = todosList;
        this.itemClickListener = itemClickListener;
    }

    public void updateTodosList(List<TodoResponse> newTodosList) {
        todos.clear();
        todos.addAll(newTodosList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {

        TextView id = holder.itemView.findViewById(R.id.id);
        TextView userid = holder.itemView.findViewById(R.id.userID);
        TextView tittle = holder.itemView.findViewById(R.id.tittle);
        TextView complete = holder.itemView.findViewById(R.id.completed);

        id.setText(todos.get(position).id);
        userid.setText(todos.get(position).userId);
        tittle.setText(todos.get(position).title);
        complete.setSelected(todos.get(position).completed);

        holder.itemView.setOnClickListener(view -> {
            Boolean completed = todos.get(position).completed;

            String result = "";
            if (completed != null) {
                if (completed)
                    result = "true";
                else
                    result = "false";
            }
            itemClickListener.onToDoClicked(result);
        });

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
