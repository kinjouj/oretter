package kinjouj.app.oretter;

import android.support.v7.widget.RecyclerView;

import kinjouj.app.oretter.util.LayoutManagerUtil;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal    = 0;
    private int visibleThreshold = 0;
    private int currentPage      = 1;
    private boolean loading      = true;

    int firstVisibleItem;
    int visibleItemCount;
    int totalItemCount;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount   = layoutManager.getItemCount();
        firstVisibleItem = LayoutManagerUtil.findFirstVisibleItemPosition(layoutManager);

        if (loading && totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int current_page);

}
