package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.tasks_mvvm_databinding.R;
import ru.trubin23.tasks_mvvm_databinding.ViewModelHolder;

public class MainActivity extends AppCompatActivity {

    private static final String TASKS_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);

        TasksFragment tasksFragment = findOrCreateFragment();

        TasksViewModel viewModel = findOrCreateViewModel();

        //tasksFragment.setViewModel(viewModel);
    }

    private TasksFragment findOrCreateFragment() {
        return null;
    }

    private TasksViewModel findOrCreateViewModel() {
        ViewModelHolder<TasksViewModel> retainedViewModel =
                (ViewModelHolder<TasksViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(TASKS_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null){
            return retainedViewModel.getViewModel();
        } else {
            return null;
        }
    }
}
