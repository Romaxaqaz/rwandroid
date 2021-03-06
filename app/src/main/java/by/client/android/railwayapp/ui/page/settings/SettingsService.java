package by.client.android.railwayapp.ui.page.settings;

import android.content.Context;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.model.Settings;

/**
 * Класс для работы с настройками приложения.
 *
 * @author Q-RPA
 */
public class SettingsService {

    private static final String URL_KEY = "BASE_URL_KEY";

    private static final String SCOREBOARD_STATION_KEY = "SCOREBOARD_STATION_KEY";

    private SettingSharedPreferences sharedPreferences;
    private ApplicationProperties applicationProperties;
    private Settings settings;

    public SettingsService(Context context) {
        applicationProperties = new ApplicationProperties(context);
        sharedPreferences = new SettingSharedPreferences(context);

        initParams();
    }

    private void initParams() {
        String url = sharedPreferences.getValue(URL_KEY, applicationProperties.get(URL_KEY));

        settings = new Settings(url);
    }

    /**
     * Сохраняет настройки приложения.
     *
     * @param settings модель настроек {@link Settings}.
     */
    public void saveSettings(Settings settings) {
        this.settings = settings;

        sharedPreferences.setValue(URL_KEY, settings.getUrl());
    }

    /**
     * Получает настройки приложения {@link Settings}.
     *
     * @return возвращает модель настроек приложения.
     */
    public Settings getSettings() {
        return settings;
    }

    public void saveScoreboardStation(ScoreboardStation station) {
        sharedPreferences.setValue(SCOREBOARD_STATION_KEY, String.valueOf(station));
    }

    public ScoreboardStation getScoreboardStation() {
        String station = sharedPreferences.getValue(SCOREBOARD_STATION_KEY, String.valueOf(ScoreboardStation.MINSK));
        return ScoreboardStation.valueOf(station);
    }
}
