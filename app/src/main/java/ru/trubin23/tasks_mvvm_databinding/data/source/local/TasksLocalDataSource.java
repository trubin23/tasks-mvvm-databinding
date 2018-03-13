package ru.trubin23.tasks_mvvm_databinding.data.source.local;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;

/**
 * Created by Andrey on 12.03.2018.
 */

public interface TasksLocalDataSource extends TasksDataSource{

    void deleteAllTasks();
}
