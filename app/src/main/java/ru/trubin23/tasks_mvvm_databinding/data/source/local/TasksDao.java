package ru.trubin23.tasks_mvvm_databinding.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.trubin23.tasks_mvvm_databinding.data.Task;

/**
 * Created by Andrey on 13.03.2018.
 */

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    List<Task> getTasks();

    @Query("SELECT * FROM tasks WHERE task_id = :taskId")
    Task getTaskById(String taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("UPDATE tasks SET completed = :completed WHERE task_id = :taskId")
    void updateCompleted(String taskId, boolean completed);

    @Query("DELETE FROM tasks WHERE task_id = :taskId")
    int deleteTaskById(String taskId);

    @Query("DELETE FROM tasks WHERE completed = 1")
    int deleteCompletedTasks();

    @Query("DELETE FROM tasks")
    void deleteTasks();
}
