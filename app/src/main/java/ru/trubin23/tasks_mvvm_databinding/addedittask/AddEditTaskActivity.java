package ru.trubin23.tasks_mvvm_databinding.addedittask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.trubin23.tasks_mvvm_databinding.Injection;
import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.ViewModelHolder;
import ru.trubin23.tasks_mvvm_databinding.taskdetail.TaskDetailFragment;
import ru.trubin23.tasks_mvvm_databinding.taskdetail.TaskDetailViewModel;
import ru.trubin23.tasks_mvvm_databinding.util.ActivityUtils;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final String ADD_EDIT_TASK_ID = "ADD_EDIT_TASK_ID";

    private static final String ADDEDITTASK_VIEWMODEL_TAG = "ADDEDITTASK_VIEWMODEL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_task_act);

        setupToolbar();
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
    private AddEditTaskFragment findOrCreateFragment() {
        String taskId = getIntent().getStringExtra(ADD_EDIT_TASK_ID);

        AddEditTaskFragment addEditTaskFragment =
                (AddEditTaskFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.content_frame);
        if (addEditTaskFragment == null) {
            addEditTaskFragment = AddEditTaskFragment.newInstance(taskId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditTaskFragment, R.id.content_frame);
        }
        return addEditTaskFragment;
    }

    @NonNull
    private AddEditTaskViewModel findOrCreateViewModel() {
        //noinspection unchecked
        ViewModelHolder<AddEditTaskViewModel> retainedViewModel =
                (ViewModelHolder<AddEditTaskViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(ADDEDITTASK_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            return retainedViewModel.getViewModel();
        } else {
            AddEditTaskViewModel viewModel = new AddEditTaskViewModel(
                    Injection.provideTasksRepository(getApplicationContext()),
                    getApplicationContext()
            );

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    ADDEDITTASK_VIEWMODEL_TAG);

            return viewModel;
        }
    }
}
