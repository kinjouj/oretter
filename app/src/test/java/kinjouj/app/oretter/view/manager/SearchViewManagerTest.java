package kinjouj.app.oretter.view.manager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import kinjouj.app.oretter.BuildConfig;
import kinjouj.app.oretter.MainActivity;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class SearchViewManagerTest {

    private SearchViewManager manager;

    @Before
    public void setUp() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        manager = activity.getSearchViewManager();
    }

    @Test
    public void test_iconified() {
        assertThat(manager.isIconified(), is(true));
        manager.expand();
        assertThat(manager.isIconified(), is(false));
        manager.collapse();
        assertThat(manager.isIconified(), is(true));
    }

    @Test
    public void test_query() {
        SearchViewManager _manager = Mockito.spy(manager);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                assertThat(args[0].toString(), is("A"));
                return null;
            }
        }).when(_manager).search(anyString());
        _manager.onQueryTextSubmit("A");
        verify(_manager, times(1)).search(anyString());
    }

    @Test
    public void test_unbind() {
        assertThat(manager.getView(), notNullValue());
        manager.unbind();
        assertThat(manager.getView(), nullValue());
    }
}