package ro.nepa.site.CKAN.Properties;

import ro.nepa.site.entities.Station;
import ro.nepa.site.services.DefaultStationServiceImpl;
import ro.nepa.site.services.StationService;

import javax.inject.Inject;

/**
 * <p>
 * Abstract class for structuring information about dataset and fileupload descriptions and tags. <br/>
 * Each child of this class must implement toString() method, witch is the implementation of the description and tags
 * </p>
 */

public abstract class DatasetDescription {

    protected final int startYear = 2007;
    protected final int endYear = 2014;
    protected int stationIndex;
    protected String stationInternationalCode;
    protected String stationLocationName;
    protected StringBuilder tags = new StringBuilder();
    protected final String delimitation = ",";

    public DatasetDescription() {
        tags.append("calitate aer");
        tags.append(delimitation);
        tags.append("statii automate");
        tags.append(delimitation);

    }

    public abstract String toString();

    public abstract String getTags();

    public String getStationInternationalCode() {
        return stationInternationalCode;
    }

    public void setStationInternationalCode(String stationInternationalCode) {
        this.stationInternationalCode = stationInternationalCode;
    }

    public String getStationLocationName() {
        return stationLocationName;
    }

    public void setStationLocationName(String stationLocationName) {
        this.stationLocationName = stationLocationName;
    }
}
