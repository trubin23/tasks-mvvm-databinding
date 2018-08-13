package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

public class AddEditTaskViewModel {

    public final ObservableField<String> mTitle = new ObservableField<>();

    public final ObservableField<String> mDescription = new ObservableField<>();

    public final ObservableBoolean mDataLoading = new ObservableBoolean(false);

}
