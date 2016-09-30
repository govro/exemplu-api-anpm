package ro.nepa.site.CKAN.Entities;

import ro.nepa.site.entities.Station;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>Simple class for relevant informations from the CKAN portal on the event of DATASET_CREATE</p>
 */

@Entity
@Table
        (
                name = "DatasetResponse",
                indexes = @Index(name = "name_index", columnList = "name")
        )
public class DatasetResponse extends RawResponse {
    private long datasetResponseID;
    private String id;
    private Calendar metadata_created;
    private Calendar metadata_modified;
    private String creator_user_id;
    private String name;
    private String notes;
    private String title;
    private String revision_id;
    private List<FileuploadResponse> fileuploadResponseList = new ArrayList<>();
    private Station station;
    private Calendar dateUploaded;


    @Basic
    @Column(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DatasetResponseID")
    public long getDatasetResponseID() {
        return datasetResponseID;
    }

    public void setDatasetResponseID(long datasetResponseID) {
        this.datasetResponseID = datasetResponseID;
    }

    @Basic
    @Column(name = "MetadataCreated")
    public Calendar getMetadata_created() {
        return metadata_created;
    }

    public void setMetadata_created(Calendar metadata_created) {
        this.metadata_created = metadata_created;
    }

    @Basic
    @Column(name = "MetadataUpdated")
    public Calendar getMetadata_modified() {
        return metadata_modified;
    }

    public void setMetadata_modified(Calendar metadata_modified) {
        this.metadata_modified = metadata_modified;
    }

    @Basic
    @Column(name = "CreatorUserId")
    public String getCreator_user_id() {
        return creator_user_id;
    }

    public void setCreator_user_id(String creator_user_id) {
        this.creator_user_id = creator_user_id;
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
    @Column(name = "Notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "RevisionID")
    public String getRevision_id() {
        return revision_id;
    }

    public void setRevision_id(String revision_id) {
        this.revision_id = revision_id;
    }

    @OneToMany(mappedBy = "datasetResponse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<FileuploadResponse> getFileuploadResponseList() {
        return fileuploadResponseList;
    }

    public void setFileuploadResponseList(List<FileuploadResponse> fileuploadResponseList) {
        this.fileuploadResponseList = fileuploadResponseList;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "StationId")
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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