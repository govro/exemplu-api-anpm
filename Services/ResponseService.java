package ro.nepa.site.CKAN.Services;

import ro.nepa.site.CKAN.Entities.DatasetResponse;
import ro.nepa.site.CKAN.Entities.ErrorResponse;
import ro.nepa.site.CKAN.Entities.FileuploadResponse;
import ro.nepa.site.entities.Station;

/**
 * ResponseService interface declaration.
 */

public interface ResponseService {

    DatasetResponse saveDatasetResponse(DatasetResponse datasetResponse);

    ErrorResponse saveErrorResponse(ErrorResponse response);

    DatasetResponse findDatasetForStation(Station station);

    FileuploadResponse saveFileuploadResponse(FileuploadResponse fileuploadResponse);
}
