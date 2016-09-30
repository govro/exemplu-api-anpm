package ro.nepa.site.CKAN.Services;

import ro.nepa.site.CKAN.CKANSession;
import ro.nepa.site.CKAN.Properties.DatasetDescription;
import ro.nepa.site.CKAN.Properties.DatasetProperties;
import ro.nepa.site.CKAN.Entities.DatasetResponse;
import ro.nepa.site.CKAN.Entities.FileuploadResponse;
import ro.nepa.site.CKAN.Properties.FileuploadProperties;
import ro.nepa.site.CKAN.Entities.RawResponse;
import ro.nepa.site.entities.Station;
import ro.nepa.site.entities.StationXmlFile;

import java.io.IOException;

/**
 * <p>
 * Generic interface for handling {@link DatasetResponse} and {@link FileuploadResponse} data manipulation.
 * </p>
 */

public interface UploadService {

    /**
     * <p>
     * Creates a new dataset on the CKAN portal based on the properties provided as arguments.
     * </p>
     *
     * @param stationIndex Station's identifier.
     */
    boolean uploadDataset(int stationIndex) throws IOException;

    /**
     * <p>
     * Uploads all the {@link ro.nepa.site.entities.StationXmlFile} associated with a station. <br/>
     * The {@link java.util.List} is found by mean of {@link Station} getStationXmlFile() method <br/>
     * Major constraint when using this method is that the target dataset is automatically selected based on the same station.
     * </p>
     */
    void uploadHistoricalFiles(int stationIndex);

    /**
     * <p>
     * Uploads a single file to CKAN dataset. <br/>
     * Dataset identifier is obtained by direct refernces between {@link StationXmlFile and {@link Station}}
     * </p>
     */
    boolean uploadHistoricalFile(Station station, StationXmlFile file);

    /**
     * <p>
     * Generates a description for a dataset based on station index. <br/>
     * This method is useful for historical values.
     * </p>
     *
     * @param station Station that creates the custom description title <br/>
     * @return Description object customized with station attributes
     */
    DatasetDescription generateDescription(Station station);

    boolean uploadHistoricalFilesYear(int stationIndex, int year);

    /**
     * <p>
     * Retries to upload files that could not be sent before.
     * </p>
     */

    //TODO: Implement this method for all uploads that could not be finished
    boolean uploadHistoricalFilesRetry();

    /**
     * <p>
     *      Use this method for uploading all files regarding to a station. <br/>
     *      Includes also protection for datasets, meaning it will use existing dataset if available
     * </p>
     */
    void uploadStationHistoricalData(int stationIndex);


}
