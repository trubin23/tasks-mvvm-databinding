package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.data.Task;

/**
 * Created by Andrey on 16.03.2018.
 */

public class TaskItemViewModel extends BaseObservable {

    private final Context mContext;

    private final ObservableField<Task> mTaskObservable = new ObservableField<>();

    TaskItemViewModel(@NonNull Context context) {
        mContext = context;
    }

    void setTask(@NonNull Task task) {
        mTaskObservable.set(task);
    }

    public void taskClicked() {

    }

    @Bindable
    public boolean getCompleted() {
        Task task = mTaskObservable.get();
        return task != null && task.isCompleted();
    }

    @Bindable
    public String getTitleForItem() {
        Task task = mTaskObservable.get();
        if (task == null) {
            return mContext.getString(R.string.no_data);
        }
        return task.getTitle();
    }
}
