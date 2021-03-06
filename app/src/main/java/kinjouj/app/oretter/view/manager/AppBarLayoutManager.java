package kinjouj.app.oretter.view.manager;

import android.view.View;
import android.support.design.widget.AppBarLayout;

public class AppBarLayoutManager extends ViewManager<AppBarLayout> {

    AppBarLayout.OnOffsetChangedListener listener;

    public AppBarLayoutManager(View view) {
        super(view);
    }

    public void addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener) {
        if (this.listener == null) {
            getView().addOnOffsetChangedListener(listener);
            this.listener = listener;
        }
    }

    public void removeOnOffsetChangedListener() {
        if (listener != null) {
            getView().removeOnOffsetChangedListener(listener);
            listener = null;
        }
    }

    @Override
    public void unbind() {
        removeOnOffsetChangedListener();
    }
}
