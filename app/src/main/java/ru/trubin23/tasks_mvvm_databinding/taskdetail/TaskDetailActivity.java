package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.ViewModelHolder;
import ru.trubin23.tasks_mvvm_databinding.util.ActivityUtils;

public class TaskDetailActivity extends AppCompatActivity implements TaskDetailNavigator {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    private static final String TASKDETAIL_VIEWMODEL_TAG = "TASKDETAIL_VIEWMODEL_TAG";

    private TaskDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_act);

        setupToolbar();

        TaskDetailFragment taskDetailFragment = findOrCreateFragment();

        TaskDetailViewModel taskDetailViewModel = findOrCreateViewModel();
        taskDetailViewModel.setNavigator(this);

        taskDetailFragment.setViewModel(taskDetailViewModel);
    }

    @Override
    protected void onDestroy() {
        mViewModel.onActivityDestroyed();
        super.onDestroy();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @NonNull
    private TaskDetailFragment findOrCreateFragment() {
        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        TaskDetailFragment taskDetailFragment =
                (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (taskDetailFragment == null) {
            taskDetailFragment = TaskDetailFragment.newInstance(taskId);
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

    @Override
    public void onTaskDeleted() {

    }

    @Override
    public void onStartEditTask() {

    }
}
