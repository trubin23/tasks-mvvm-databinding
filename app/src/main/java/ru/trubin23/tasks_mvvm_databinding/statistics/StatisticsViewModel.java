package ru.trubin23.tasks_mvvm_databinding.statistics;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

public class StatisticsViewModel {

    public final ObservableBoolean mDataLoading = new ObservableBoolean(false);

    private int mNumberOfActiveTasks = 0;

    private int mNumberOfCompletedTasks = 0;

    @Bindable
    public boolean isEmpty() {
        return mNumberOfActiveTasks + mNumberOfCompletedTasks == 0
    }
}
