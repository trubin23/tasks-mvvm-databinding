package ru.trubin23.tasks_mvvm_databinding.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.trubin23.tasks_mvvm_databinding.R;

public class MainActivity extends AppCompatActivity {

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
        return null;
    }
}
