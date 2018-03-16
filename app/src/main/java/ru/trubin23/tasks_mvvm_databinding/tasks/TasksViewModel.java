package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 11.03.2018.
 */

public class TasksViewModel extends BaseObservable {

    private TasksRepository mTasksRepository;
    private Context mContext;

    TasksViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context.getApplicationContext();
    }
}
