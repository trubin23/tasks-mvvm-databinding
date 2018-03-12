package ru.trubin23.tasks_mvvm_databinding;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvvm_databinding.data.source.TasksRepository;

/**
 * Created by Andrey on 12.03.2018.
 */

public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        return null;
    }
}
