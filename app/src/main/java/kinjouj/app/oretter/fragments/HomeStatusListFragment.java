package kinjouj.app.oretter.fragments;

import java.util.Collections;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import twitter4j.Paging;
import twitter4j.Status;

import kinjouj.app.oretter.AppInterfaces;
import kinjouj.app.oretter.view.adapter.StatusRecyclerViewAdapter;

public class HomeStatusListFragment extends RecyclerViewFragment<Status> {

    private static final String TAG = HomeStatusListFragment.class.getName();

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new StatusRecyclerViewAdapter(getActivity());
    }

    @Override
    public List<Status> fetch(int page) {
        List<Status> statuses = null;

        try {
            statuses = getTwitter().getHomeTimeline(new Paging(page));
        } catch (Exception e) {
            e.printStackTrace();
            statuses = Collections.<Status>emptyList();
        }

        return statuses;
    }
}
