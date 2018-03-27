package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 27.03.2018.
 */

public class TaskDetailViewModel extends BaseObservable {

    private final TasksRepository mTasksRepository;

    private final Context mContext;

    TaskDetailViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context;
    }
}
