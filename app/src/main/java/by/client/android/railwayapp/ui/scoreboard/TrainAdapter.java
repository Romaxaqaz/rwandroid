package by.client.android.railwayapp.ui.scoreboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;

/**
 * Адаптер для отображения элемента списка поездов
 *
 * @author ROMAN PANTELEEV
 */
class TrainAdapter extends ModifiableRecyclerAdapter<TrainAdapter.ViewHolder, Train> {

    TrainAdapter() {
        super(R.layout.train_item);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bind(ViewHolder holder, int position) {
        Train train = getItems().get(position);

        holder.icon.setBackgroundResource(new TrainTypeToImage().convert(train.getPathType()));
        holder.id.setText(train.getId());
        holder.path.setText(train.getPath());
        holder.type.setText(train.getTrainType());
        holder.way.setText(train.getWay());
        holder.platform.setText(train.getPlatform());
        holder.start.setText(train.getStart());
        holder.end.setText(train.getEnd());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView id;
        private TextView path;
        private TextView type;
        private TextView way;
        private TextView platform;
        private TextView start;
        private TextView end;

        ViewHolder(View view) {
            super(view);

            icon = view.findViewById(R.id.icon);
            id = view.findViewById(R.id.id);
            path = view.findViewById(R.id.path);
            type = view.findViewById(R.id.type);
            way = view.findViewById(R.id.way);
            platform = view.findViewById(R.id.paltform);
            start = view.findViewById(R.id.start);
            end = view.findViewById(R.id.end);
        }

    }
}