package ru.trubin23.tasks_mvvm_databinding.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by Andrey on 12.03.2018.
 */

@Entity(tableName = "tasks")
public final class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "task_id")
    private final String mTaskId;

    @NonNull
    @ColumnInfo(name = "title")
    private final String mTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private final String mDescription;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    public Task(@NonNull String title, @NonNull String description,
                @NonNull String taskId, boolean completed) {
        mTitle = title;
        mDescription = description;
        mTaskId = taskId;
        mCompleted = completed;
    }

    public Task(@NonNull String title, @NonNull String description, @NonNull String taskId) {
        this(title, description, taskId, false);
    }

    public Task(@NonNull String title, @NonNull String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }

    @NonNull
    public String getTaskId() {
        return mTaskId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }
}
