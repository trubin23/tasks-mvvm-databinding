package ru.trubin23.tasks_mvvm_databinding;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import ru.trubin23.tasks_mvvm_databinding.tasks.TasksViewModel;

public class SwipeRefreshLayoutDataBinding {

    @BindingAdapter("android:onRefresh")
    public static void setSwipeRefreshLayoutOnRefreshListener(SwipeRefreshLayout view,
                                                              final TasksViewModel viewModel) {
        view.setOnRefreshListener(() -> viewModel.loadTasks(true));
    }
}
