package by.client.android.railwayapp.model;

import java.io.Serializable;
import java.util.Date;

import by.client.android.railwayapp.api.rw.model.SearchStantion;

/**
 * Модель запроса поездов
 *
 * @author ROMAN PANTELEEV
 */
public class SearchTrain implements Serializable {

    /**
     * Станция отправления
     */
    private SearchStantion departureStation;

    /**
     * Куда
     */
    private SearchStantion destinationStantion;

    /**
     * Дата отправления
     */
    private Date departureDate;

    public SearchTrain(SearchStantion departureStation, SearchStantion destinationStantion, Date departureDate) {
        this.departureStation = departureStation;
        this.destinationStantion = destinationStantion;
        this.departureDate = departureDate;
    }

    public SearchStantion getDepartureStation() {
        return departureStation;
    }

    public SearchStantion getDestinationStantion() {
        return destinationStantion;
    }

    public Date getDepartureDate() {
        return departureDate;
    }
}
