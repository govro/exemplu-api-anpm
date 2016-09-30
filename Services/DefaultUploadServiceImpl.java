package ro.nepa.site.CKAN.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.nepa.site.CKAN.*;
import ro.nepa.site.CKAN.Converters.DatasetResponseConverter;
import ro.nepa.site.CKAN.Converters.ErrorResponseConverter;
import ro.nepa.site.CKAN.Converters.FileuploadResponseConverter;
import ro.nepa.site.CKAN.Entities.DatasetResponse;
import ro.nepa.site.CKAN.Entities.ErrorResponse;
import ro.nepa.site.CKAN.Entities.FileuploadResponse;
import ro.nepa.site.CKAN.Entities.RawResponse;
import ro.nepa.site.CKAN.Properties.*;
import ro.nepa.site.CKAN.Repositories.DatasetResponseRepository;
import ro.nepa.site.CKAN.Repositories.FileuploadResponseRepository;
import ro.nepa.site.entities.Station;
import ro.nepa.site.entities.StationXmlFile;
import ro.nepa.site.repositories.StationXmlFileRepository;
import ro.nepa.site.services.StationService;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

/**
 * <p>
 * Service implementation for specific business operations on {@link DatasetResponse} and {@link FileuploadResponse} responses
 * </p>
 */

@Service
public class DefaultUploadServiceImpl implements UploadService {

    @Inject
    StationService stationService;

    @Inject
    CKANSession nepaSession;

    @Inject
    ResponseService responseService;

    @Inject
    StationXmlFileRepository fileRepository;

    @PersistenceContext
    EntityManager entityManager;

    Logger logger = LogManager.getLogger(getClass().getName());


    @Override
    public boolean uploadDataset(int stationIndex) {

        Station station = stationService.findStationByIndex(stationIndex);
        DatasetDescription description = generateDescription(station);

        RawResponse response;

        try {
            response = nepaSession.uploadDataset(new AirQualityDatasetProperties(description));
        } catch (IOException exception) {
            throw new RuntimeException("IO Exception trying to upload dataset " + description.getStationInternationalCode());
        }

        if (response.isValid()) {
            DatasetResponse datasetResponse = new DatasetResponseConverter().convert(response);
            datasetResponse.setStation(station);
            try {
                responseService.saveDatasetResponse(datasetResponse);
                logger.info("INFO: Dataset saved! ");
            } catch (Exception e) {
                logger.error("ERROR: error occured; " + e.getMessage());
            }

            return true;

        } else {
            ErrorResponse errorResponse = new ErrorResponseConverter().convert(response);
            errorResponse.setDatasetTitle(description.toString());
            responseService.saveErrorResponse(errorResponse);
            logger.error(String.format("Dataset %s upload failed! error saved. Entity manager will flush.", description.getStationLocationName()));

            return false;
        }
    }

    @Transactional
    @Override
    public void uploadHistoricalFiles(int stationIndex) {

        Station station = stationService.findStationByIndex(stationIndex);
        List<StationXmlFile> files = station.getStationXmlFileList();


        for (StationXmlFile file : files) {
            uploadHistoricalFile(station, file);
        }
    }

    @Transactional
    @Override
    public boolean uploadHistoricalFilesYear(int stationIndex, int year) {

        Station station = stationService.findStationByIndex(stationIndex);
        List<StationXmlFile> files = fileRepository.findByStationAndXmlYear(station, year);

        for (StationXmlFile file : files) {
            uploadHistoricalFile(station, file);
        }

        return true;
    }

    @Override
    public boolean uploadHistoricalFile(Station station, StationXmlFile file) {

        DatasetResponse datasetResponse = responseService.findDatasetForStation(station);

        if (datasetResponse == null) {
            return false;
        }

        boolean succes = false;

        logger.info("#### Start file processing: " + file.getFilename());

        FileuploadProperties properties = new FileuploadProperties();
        properties.setName("Valori validate aferente " + file.getXmlPeriod() + ",  anul " + file.getXmlYear());
        properties.setUpload(file.getContent());
        properties.setUrl("");
        properties.setPackage_id(datasetResponse.getName());
        properties.setDescription("Valorile validate ale statiei automate de monitorizare a calitatii aerului " +
                station.getStationInternationalCode() + " pentru perioada: "
                + file.getXmlPeriod() + " din " + file.getXmlYear());
        properties.setAuthor("Agentia Nationala pentru Protectia Mediului (ANPM)");
        properties.setTitle(file.getFilename());

        RawResponse response = nepaSession.uploadFiles(properties);

        if (response.isValid()) {
            FileuploadResponse result = new FileuploadResponseConverter().convert(response);
            result.setStationXmlFile(file);
            result.setDatasetResponse(datasetResponse);
            responseService.saveFileuploadResponse(result);

            succes = true;

            logger.info("### File saved successfully: %s", file.getFilename());

        } else {
            ErrorResponse errorResponse = new ErrorResponseConverter().convert(response);
            errorResponse.setDatasetTitle(file.getFilename());
            errorResponse.setStationXmlFile(file);
            responseService.saveErrorResponse(errorResponse);

            logger.error(String.format("File upload error saved!"));

            return false;
        }

        logger.info("#### GO TO SLEEP! ");

        try {
            Thread.currentThread();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //ignore
        }

        return succes;
    }

    @Override
    public DatasetDescription generateDescription(Station station) {
        DatasetDescription description = new HistoricalValuesDatasetDescription();
        description.setStationInternationalCode(station.getStationInternationalCode());
        description.setStationLocationName(station.getStationLocationName());

        return description;
    }

    @Override
    public boolean uploadHistoricalFilesRetry() {
        return false;
    }

    @Override
    @Transactional
    public void uploadStationHistoricalData(int stationIndex) {
        Station station = stationService.findStationByIndex(stationIndex);
        DatasetResponse datasetResponse = responseService.findDatasetForStation(station);

        if (datasetResponse == null) {
            uploadDataset(stationIndex);
        }

        uploadHistoricalFiles(stationIndex);
    }
}
