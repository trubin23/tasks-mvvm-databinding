package ru.trubin23.tasks_mvvm_databinding.data.source.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ru.trubin23.tasks_mvvm_databinding.data.Task;
import ru.trubin23.tasks_mvvm_databinding.data.source.TasksDataSource;

/**
 * Created by Andrey on 14.03.2018.
 */

public class TasksCacheRepository implements TasksCacheDataSource {

    private static TasksCacheRepository INSTANCE;

    private Map<String, Task> mCachedTask;

    private boolean mCacheIsDirty = false;

    private TasksCacheRepository() {
        mCachedTask = new LinkedHashMap<>();
    }

    public static TasksCacheRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksCacheRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksCacheRepository();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean isDirty() {
        return mCacheIsDirty;
    }

    private boolean cacheAvailable() {
        return mCacheIsDirty || mCachedTask == null || mCachedTask.isEmpty();
    }

    @Nullable
    @Override
    public List<Task> getTasks() {
        if (cacheAvailable()) {
            return null;
        } else {
            return new ArrayList<>(mCachedTask.values());
        }
    }
}
