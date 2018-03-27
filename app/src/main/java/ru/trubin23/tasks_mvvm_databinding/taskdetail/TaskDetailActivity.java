package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.ViewModelHolder;
import ru.trubin23.tasks_mvvm_databinding.tasks.TasksFragment;
import ru.trubin23.tasks_mvvm_databinding.tasks.TasksViewModel;
import ru.trubin23.tasks_mvvm_databinding.util.ActivityUtils;

public class TaskDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    private static final String TASKDETAIL_VIEWMODEL_TAG = "TASKDETAIL_VIEWMODEL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_act);

        TaskDetailFragment taskDetailFragment = findOrCreateFragment();

        TaskDetailViewModel taskDetailViewModel = findOrCreateViewModel();

        taskDetailFragment.setViewModel(taskDetailViewModel);
    }

    @NonNull
    private TaskDetailFragment findOrCreateFragment() {
        TaskDetailFragment taskDetailFragment =
                (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (taskDetailFragment == null) {
            taskDetailFragment = new TaskDetailFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.content_frame);
        }
        return taskDetailFragment;
    }

    @NonNull
    private TaskDetailViewModel findOrCreateViewModel() {
        //noinspection unchecked
        ViewModelHolder<TaskDetailViewModel> retainedViewModel =
                (ViewModelHolder<TaskDetailViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(TASKDETAIL_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            return retainedViewModel.getViewModel();
        } else {
            TaskDetailViewModel viewModel = new TaskDetailViewModel(
                    Injection.provideTasksRepository(getApplicationContext()),
                    getApplicationContext()
            );

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    TASKDETAIL_VIEWMODEL_TAG);

            return viewModel;
        }
    }
}
