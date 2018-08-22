package ru.trubin23.tasks_mvvm_databinding.statistics;

import android.support.v4.app.Fragment;

import ru.trubin23.tasks_mvvm_databinding.databinding.StatisticsFragBinding;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel mViewModel;

    private StatisticsFragBinding mStatisticsFragBinding;

    public StatisticsFragment() {
    }

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    public void setViewModel(StatisticsViewModel viewModel) {
        mViewModel = viewModel;
    }
}
