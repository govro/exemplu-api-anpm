package ro.nepa.site.CKAN.Properties;

/**
 * <p>
 * Class that handles description and tags for dataset historical values
 * </p>
 */
public class HistoricalValuesDatasetDescription extends DatasetDescription {


    public HistoricalValuesDatasetDescription() {
        super();
        tags.append("valori istorice validate");
        tags.append(delimitation);
        tags.append("statia " + stationLocationName);
    }

    public String toString() {
        return "Statia " + stationInternationalCode +
                ": Lista masuratorilor validate privind calitatea aerului, perioada " + startYear + " - " + endYear;
    }

    @Override
    public String getTags() {
        return tags.toString();
    }
}
