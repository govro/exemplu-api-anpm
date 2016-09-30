package ro.nepa.site.CKAN.Properties;

import org.apache.http.entity.mime.content.FileBody;

/**
 * <p>Implements the attributes required to upload files to CKAN. <br/>
 * Each file upload is related to the dataset by means of package_id</p>
 */
public class FileuploadProperties  {

    private String package_id;
    private String url;
    private String name;
    private String author;
    private String description;
    private String title;
    private byte[] upload;

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getUpload() {
        return upload;
    }

    public void setUpload(byte[] upload) {
        this.upload = upload;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
