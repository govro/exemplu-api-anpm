package ro.nepa.site.CKAN.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.nepa.site.CKAN.Entities.DatasetResponse;
import ro.nepa.site.CKAN.Entities.ErrorResponse;
import ro.nepa.site.CKAN.Entities.FileuploadResponse;
import ro.nepa.site.CKAN.Repositories.DatasetResponseRepository;
import ro.nepa.site.CKAN.Repositories.ErrorResponseRepository;
import ro.nepa.site.CKAN.Repositories.FileuploadResponseRepository;
import ro.nepa.site.entities.Station;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;

/**
 * Default implementation for ResponseService service
 */
@Service
public class DefaultResponeServiceImpl implements ResponseService {

    @Inject
    DatasetResponseRepository datasetRepository;
    @Inject
    ErrorResponseRepository errorResponseRepository;
    @Inject
    FileuploadResponseRepository fileuploadResponseRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public DatasetResponse saveDatasetResponse(DatasetResponse datasetResponse) {
        datasetResponse.setDateUploaded(Calendar.getInstance());
        DatasetResponse result = datasetRepository.save(datasetResponse);
        entityManager.flush();
        return result;
    }

    @Override
    @Transactional
    public ErrorResponse saveErrorResponse(ErrorResponse response) {

        response.setDateCreated(Calendar.getInstance());
        ErrorResponse result = errorResponseRepository.save(response);
        entityManager.flush();

        return result;
    }

    @Override
    public DatasetResponse findDatasetForStation(Station station) {
        if (datasetRepository.countByStation(station) == 0) {
            return null;
        }

        if (datasetRepository.countByStation(station) > 1) {
            return null;
        }

        return datasetRepository.findByStation(station);
    }

    @Override
    public FileuploadResponse saveFileuploadResponse(FileuploadResponse fileuploadResponse) {
        fileuploadResponse.setDateUploaded(Calendar.getInstance());
        return fileuploadResponseRepository.save(fileuploadResponse);
    }
}
