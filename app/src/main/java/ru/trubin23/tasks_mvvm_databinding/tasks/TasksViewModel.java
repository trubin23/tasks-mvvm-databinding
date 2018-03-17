package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 11.03.2018.
 */

public class TasksViewModel extends BaseObservable {

    private final TasksRepository mTasksRepository;
    private final Context mContext;

    public final ObservableList<Task> mTasks = new ObservableArrayList<>();

    public final ObservableField<String> currentFilteringLabel = new ObservableField<>();

    TasksViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
        mTasksRepository = repository;
        mContext = context.getApplicationContext();
    }

    @Bindable
    public boolean isEmpty(){
        return mTasks.isEmpty();
    }


}
