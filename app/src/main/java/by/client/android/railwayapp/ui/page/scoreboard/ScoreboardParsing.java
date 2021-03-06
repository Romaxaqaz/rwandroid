package by.client.android.railwayapp.ui.page.scoreboard;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.support.base.BaseParsing;

/**
 * Класс дял парсинга поездов станции
 * <p>
 * <p>Произвродит парсинг html-страницы</p>
 *
 * @author ROMAN PANTELEEV
 */
class ScoreboardParsing extends BaseParsing<Train, String> {

    private static final String TRAIN_ID = "small[class=train_id]";
    private static final String PATH_ICO = "i[class]";
    private static final String PATH = "span[class=train_text]";
    private static final String TRAIN_TYPE = "div[class=train_description]";
    private static final String TIME_END = "b[class=train_end-time]";
    private static final String TIME_START = "b[class=train_start-time]";
    private static final String START_NUMBERING = "td[class=train_item train_halts train_number]";
    private static final String WAY = "td[class=train_item train_halts train_way]";
    private static final String PLATFORM = "td[class=train_item train_halts train_platform]";

    ScoreboardParsing(String page) {
        super(page);
    }

    protected List<Train> pars() throws IOException {
        Document doc = Jsoup.parse(getParam());

        List<Train> scoreboardList = new ArrayList<>();
        Elements tableRows = doc.select(TABLE).first().select(TR);

        for (Element item : tableRows) {
            String id = checkEmpty(item.select(TRAIN_ID).first());
            if (Objects.equals(id, EMPTY_STRING)) {
                continue;
            }

            String ico = item.select(PATH_ICO).first().getAllElements().attr("class");
            String path = checkEmpty(item.select(PATH).first());
            String trainType = checkEmpty(item.select(TRAIN_TYPE).first());

            String start = checkEmpty(item.select(TIME_END).first());
            String end = checkEmpty(item.select(TIME_START).first());
            String trainNumber = checkEmpty(item.select(START_NUMBERING).first());

            String way = checkEmpty(item.select(WAY).first());
            String platform = checkEmpty(item.select(PLATFORM).first());

            Train train = Train.createBuilder()
                    .setId(id)
                    .setPath(path)
                    .setTrainType(trainType)
                    .setEnd(end)
                    .setStart(start)
                    .setNumbering(trainNumber)
                    .setWay(way)
                    .setPlatform(platform)
                    .setPathType(ico);

            scoreboardList.add(train);
        }

        return scoreboardList;
    }
}
