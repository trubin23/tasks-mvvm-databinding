package ru.trubin23.tasks_mvvm_databinding.data.source.local;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;

/**
 * Created by Andrey on 12.03.2018.
 */

public interface TasksLocalDataSource extends TasksDataSource{

    void deleteAllTasks();

    void setTasks(@NonNull List<Task> tasks);
}
