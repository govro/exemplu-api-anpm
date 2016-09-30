package ro.nepa.site.CKAN.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <p>
 * Contains the same elements that must be included in the http request
 * for creating a new dataset on the CKAN portal. <br>
 * <br><br>
 * Instance of this class is serialized to json and included in the {@link org.apache.http.HttpRequest}.
 * </p>
 */
public abstract class DatasetProperties {


    protected String name;
    protected String notes;
    protected String owner_org;
    protected String title;
    protected int version;
    //protected String tags;

    public DatasetProperties() {
    }


    /**
     * Represents the key of the dataset; this key links the datasets to the files
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Represents the notes associated with the dataset
     */
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Represents the institution code
     */
    public String getOwner_org() {
        return owner_org;
    }

    public void setOwner_org(String owner_org) {
        this.owner_org = owner_org;
    }

    /**
     * Represents the title of the dataset
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Transforms this object to a json
     * */
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String result;
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            result = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("could not transform AirQualityDatasetProperties to json");
        }

        return result;
    }

}
