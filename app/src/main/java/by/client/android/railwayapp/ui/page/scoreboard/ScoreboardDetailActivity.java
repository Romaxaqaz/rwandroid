package by.client.android.railwayapp.ui.page.scoreboard;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.jetbrains.annotations.NotNull;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.support.common.StartActivityBuilder;
import by.client.android.railwayapp.ui.widget.NewsWidget;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Страница для отображения детальной информации поезда со страницы "Виртуально табло"
 *
 * @author PRV
 */
@EActivity(R.layout.activity_scroreboard_detail)
public class ScoreboardDetailActivity extends AppCompatActivity {

    private static final String TRAIN_KEY = "TRAIN_KEY";

    @ViewById(R.id.icoImageView)
    CircleImageView icoImageView;

    @ViewById(R.id.trainIdTextView)
    TextView trainIdTextView;

    @ViewById(R.id.wayTextView)
    TextView wayTextView;

    @ViewById(R.id.pathTextView)
    TextView pathTextView;

    @ViewById(R.id.trainTypeTextView)
    TextView trainTypeTextView;

    @ViewById(R.id.platformTextView)
    TextView platformTextView;

    @ViewById(R.id.startTimeTextView)
    TextView startTimeTextView;

    @ViewById(R.id.endTimeTextView)
    TextView endTimeTextView;

    @Extra(TRAIN_KEY)
    Train train;

    public static void start(@NotNull Activity activity, @NotNull Train train, int requestCode) {
        StartActivityBuilder.create(activity, ScoreboardDetailActivity_.class)
                .param(ScoreboardDetailActivity.TRAIN_KEY, train)
                .startForResult(requestCode);
    }

    @AfterViews
    void initActivity() {
        getSupportActionBar().setHomeButtonEnabled(true);

        trainIdTextView.setText(train.getId());
        icoImageView.setImageResource(new TrainTypeToImage().convert(train.getPathType()));
        trainTypeTextView.setText(train.getTrainType());
        pathTextView.setText(train.getPath());
        wayTextView.setText(train.getWay());
        platformTextView.setText(train.getPlatform());
        startTimeTextView.setText(train.getStart());
        endTimeTextView.setText(train.getEnd());
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

    private void updateWidget() {
        Intent intent = new Intent(this, NewsWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        intent.putExtra("TRAIN_ID", train);
        sendBroadcast(intent);
    }
}
