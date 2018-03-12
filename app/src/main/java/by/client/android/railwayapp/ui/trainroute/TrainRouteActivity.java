package by.client.android.railwayapp.ui.trainroute;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.jetbrains.annotations.NotNull;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerActivity;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.support.BaseLoaderListener;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.support.common.StartActivityBuilder;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Страница отображения маршрута позда
 *
 * @author PRV
 */
@EActivity(R.layout.activity_train_route)
public class TrainRouteActivity extends BaseDaggerActivity {

    private static final String TRAIN_KEY = "TRAIN_ROUTE_KEY";

    @Inject
    Client client;

    @Inject
    RailwayApi railwayApi;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @ViewById(R.id.iconImageView)
    ImageView iconImageView;

    @ViewById(R.id.idTextView)
    TextView idTextView;

    @ViewById(R.id.pathTextView)
    TextView pathTextView;

    @ViewById(R.id.typeTextView)
    TextView typeTextView;

    @ViewById(R.id.trainRouteListView)
    RecyclerView trainRouteRecyclerView;

    @ViewById(R.id.progressBar)
    ProgressBar progressBar;

    @Extra(TRAIN_KEY)
    TrainRoute train;

    private RouteAdapter routeAdapter;

    public static void start(@NotNull Activity activity, @NotNull TrainRoute train, int requestCode) {
        StartActivityBuilder.create(activity, TrainRouteActivity_.class)
            .param(TRAIN_KEY, train)
            .startForResult(requestCode);
    }

    @AfterViews
    void initActivity() {
        getSupportActionBar().setTitle(R.string.train_route);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        routeAdapter = new RouteAdapter();
        trainRouteRecyclerView.setAdapter(routeAdapter);
        trainRouteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trainRouteRecyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData();
        initHeader();
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initHeader() {
        idTextView.setText(train.getId());
        iconImageView.setBackgroundResource(new TrainTypeToImage().convert(train.getIco()));
        typeTextView.setText(train.getTrainType());
        pathTextView.setText(train.getPath());
    }

    private void loadData() {
        client.send(new TrainRouteLoader(railwayApi, train.getId()), new TrainRouteLoadListener(this));
    }

    private static class TrainRouteLoadListener extends BaseLoaderListener<TrainRouteActivity, List<RouteItem>> {

        TrainRouteLoadListener(TrainRouteActivity reference) {
            super(reference);
        }

        @Override
        protected void onStart(TrainRouteActivity reference) {
            UiUtils.setVisibility(true, reference.progressBar);
        }

        @Override
        protected void onSuccess(TrainRouteActivity reference, List<RouteItem> routeItems) {
            reference.routeAdapter.setData(routeItems);
        }

        @Override
        protected void onError(TrainRouteActivity reference, Exception exception) {
            reference.globalExceptionHandler.handle(exception);
        }

        @Override
        protected void onFinish(TrainRouteActivity reference, boolean success) {
            UiUtils.setVisibility(false, reference.progressBar);
        }
    }
}
