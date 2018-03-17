package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.data.Task;

/**
 * Created by Andrey on 16.03.2018.
 */

public class TasksListBindings {

    @BindingAdapter("app:items")
    public static void setItems(ListView listView, List<Task> items) {
        TasksAdapter adapter = (TasksAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.setTasks(items);
        }
    }
}
