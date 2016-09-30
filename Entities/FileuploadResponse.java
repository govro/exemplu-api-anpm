package ro.nepa.site.CKAN.Entities;

import ro.nepa.site.entities.StationXmlFile;

import javax.persistence.*;
import java.util.Calendar;

/**
 * <p>Simple class for relevant informations from the CKAN portal on the event of FILE_UPLOAD</p>
 */
@Entity
@Table
        (
                name = "FileuploadResponse",
                indexes = @Index(name = "name_index", columnList = "Name")
        )
public class FileuploadResponse extends RawResponse {

    private long FileuploadResponseID;
    /**
     * <p>Contains the url to the file, for download from CKAN portal</p>
     */
    private String url;

    /**
     * <p>Contains the position of the uploaded file;</p>
     */
    private Integer position;

    /**
     * <p>Contains the id of the file; the id is for CKAN internal use</p>
     */
    private String id;

    /**
     * <p>Contains the name of the file, as returned by the CKAN portal</p>
     */
    private String name;

    /**
     * Foreign key to {@link DatasetResponse}
     */
    private DatasetResponse datasetResponse;

    /**
     * Each response contains a link to the StationXmlFile
     */
    private StationXmlFile stationXmlFile;

    private Calendar dateUploaded;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FileuploadResponseID")
    public long getFileuploadResponseID() {
        return FileuploadResponseID;
    }

    public void setFileuploadResponseID(long fileuploadResponseID) {
        FileuploadResponseID = fileuploadResponseID;
    }

    @Basic
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "DatasetResponseID")
    public DatasetResponse getDatasetResponse() {
        return datasetResponse;
    }

    public void setDatasetResponse(DatasetResponse datasetResponse) {
        this.datasetResponse = datasetResponse;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "StationXmlFileID")
    public StationXmlFile getStationXmlFile() {
        return stationXmlFile;
    }

    public void setStationXmlFile(StationXmlFile stationXmlFile) {
        this.stationXmlFile = stationXmlFile;
    }

    @Basic
    @Column(name = "DateUploaded")
    public Calendar getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Calendar dateUploaded) {
        this.dateUploaded = dateUploaded;
    }
}
