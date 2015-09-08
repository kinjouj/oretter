package kinjouj.app.oretter.view.manager;

import android.view.View;

public abstract class ViewManager<T extends View> {

    private View view;

    public ViewManager(View view) {
        this.view = view;
    }

    @SuppressWarnings("unchecked")
    public T getView() {
        return (T)view;
    }

    public void unbind() {
    }

    protected void destroyView() {
        view = null;
    }
}
