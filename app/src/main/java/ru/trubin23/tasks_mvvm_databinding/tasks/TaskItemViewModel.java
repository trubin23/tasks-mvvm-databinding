package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 16.03.2018.
 */

public class TaskItemViewModel extends BaseObservable {

    private final TasksRepository mTasksRepository;
    private final Context mContext;

    private final ObservableField<Task> mTaskObservable = new ObservableField<>();

    TaskItemViewModel(TasksRepository tasksRepository, Context context) {
        mTasksRepository = tasksRepository;
        mContext = context;
    }

    void setTask(@NonNull Task task) {
        mTaskObservable.set(task);
    }
}
