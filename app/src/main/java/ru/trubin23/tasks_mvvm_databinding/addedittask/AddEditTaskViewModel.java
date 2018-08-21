package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

public class AddEditTaskViewModel extends BaseObservable {

    public final ObservableField<String> mTitle = new ObservableField<>();

    public final ObservableField<String> mDescription = new ObservableField<>();

    public final ObservableBoolean mDataLoading = new ObservableBoolean(false);

    AddEditTaskViewModel(@NonNull TasksRepository repository, @NonNull Context context) {
    }

    public void setNavigator(AddEditTaskNavigator navigator) {
    }
}
