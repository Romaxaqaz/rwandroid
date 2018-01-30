package by.client.android.railwayapp.ui.traintimetable;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Created by PanteleevRV on 30.01.2018.
 */
public class TrainPlaceAdapter extends BaseListAdapter<Place, BaseHolder<Place>> {

    private Context context;

    /**
     * Конструктор класса {@link BaseListAdapter}
     *

     * @param context контекст данных
     */
    public TrainPlaceAdapter(Context context) {
        super(context);
        this.context = context;
        setItemLayout(R.layout.place_item);
    }

    @Override
    protected BaseHolder<Place> createHolder(View view) {
        return new TrainPlaceAdapter.ViewHolder(view);
    }

    private class ViewHolder implements BaseHolder<Place> {

        private TextView placeName;
        private TextView placeCost;
        private TextView placeCount;

        ViewHolder(View view) {
            placeName = view.findViewById(R.id.placeName);
            placeCost = view.findViewById(R.id.placeCost);
            placeCount = view.findViewById(R.id.placeCount);
        }

        @Override
        public void bind(Place place) {
            if (place != null) {
                placeName.setText(place.getName());
                placeCost.setText(place.getPrice());
                placeCount.setText(place.getFreePlaces());
            }
        }
    }
}