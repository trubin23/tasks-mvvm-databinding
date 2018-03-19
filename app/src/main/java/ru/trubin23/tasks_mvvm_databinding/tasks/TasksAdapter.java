package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.databinding.TaskItemBinding;

/**
 * Created by Andrey on 16.03.2018.
 */

public class TasksAdapter extends BaseAdapter {

    private List<Task> mTasks;

    TasksAdapter() {
        mTasks = new ArrayList<>();
        setTasks(mTasks);
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Task getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Task task = getItem(position);
        TaskItemBinding taskItemBinding;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            taskItemBinding = TaskItemBinding.inflate(inflater, viewGroup, false);
        } else {
            taskItemBinding = DataBindingUtil.getBinding(convertView);
        }

        TaskItemViewModel taskItemViewModel = new TaskItemViewModel(
                Injection.provideTasksRepository(
                        viewGroup.getContext().getApplicationContext()),
                viewGroup.getContext().getApplicationContext()
        );

        taskItemBinding.setViewModel(taskItemViewModel);

        taskItemViewModel.setTask(task);

        return taskItemBinding.getRoot();
    }

    public void setTasks(@NonNull List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }
}
