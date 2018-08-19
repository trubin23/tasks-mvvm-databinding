package ru.trubin23.tasks_mvvm_databinding.taskdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.ViewModelHolder;
import ru.trubin23.tasks_mvvm_databinding.addedittask.AddEditTaskActivity;
import ru.trubin23.tasks_mvvm_databinding.util.ActivityUtils;

public class TaskDetailActivity extends AppCompatActivity implements TaskDetailNavigator {

    public static final String TASK_DETAIL_TASK_ID = "TASK_DETAIL_TASK_ID";

    private static final String TASKDETAIL_VIEWMODEL_TAG = "TASKDETAIL_VIEWMODEL_TAG";

    private static final int REQUEST_EDIT_TASK = 1;

    private TaskDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_act);

        setupToolbar();

        TaskDetailFragment taskDetailFragment = findOrCreateFragment();

        mViewModel = findOrCreateViewModel();
        mViewModel.setNavigator(this);

        taskDetailFragment.setViewModel(mViewModel);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @NonNull
    private TaskDetailFragment findOrCreateFragment() {
        String taskId = getIntent().getStringExtra(TASK_DETAIL_TASK_ID);

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
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onStartEditTask() {
        String taskId = getIntent().getStringExtra(TASK_DETAIL_TASK_ID);
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.ADD_EDIT_TASK_ID, taskId);
        startActivityForResult(intent, REQUEST_EDIT_TASK);
    }
}
