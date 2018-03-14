package ru.trubin23.tasks_mvvm_databinding;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;
import ru.trubin23.tasks_mvvm_databinding.data.source.cache.TasksCacheRepository;
import ru.trubin23.tasks_mvvm_databinding.data.source.local.TasksDatabase;
import ru.trubin23.tasks_mvvm_databinding.data.source.local.TasksLocalRepository;
import ru.trubin23.tasks_mvvm_databinding.data.source.remote.TasksRemoteRepository;

/**
 * Created by Andrey on 12.03.2018.
 */

public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        TasksDatabase tasksDatabase = TasksDatabase.getInstance(context);

        return TasksRepository.getInstance(TasksRemoteRepository.getInstance(),
                TasksLocalRepository.getInstance(tasksDatabase.tasksDao()),
                TasksCacheRepository.getInstance());
    }
}
