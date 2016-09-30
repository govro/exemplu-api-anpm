package ro.nepa.site.CKAN;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.nepa.site.CKAN.Converters.AbstractTypeConverter;
import ro.nepa.site.CKAN.Converters.ConvertibleResponse;
import ro.nepa.site.CKAN.Entities.RawResponse;
import ro.nepa.site.CKAN.Profiles.CKANProfile;
import ro.nepa.site.CKAN.Properties.DatasetProperties;
import ro.nepa.site.CKAN.Properties.FileuploadProperties;

import java.io.*;
import java.time.Instant;

/**
 * CKAN session manager that handles through abstract response object {@link RawResponse} response for dataset upload and files upload
 */

public class CKANSession {

    private final Logger logger = LogManager.getLogger(getClass().getName());
    private CKANProfile profile;

    public CKANSession(CKANProfile profile) {
        this.profile = profile;
    }

    /**
     * <p>
     * Creates a response object ({@link RawResponse}) from dataset upload to CKAN portal. <br/>
     * Further, the response can be evaluated by isValid() method and then converted through the {@link AbstractTypeConverter}. <br/>
     * All convertible CKAN Responses implement generic interface {@link ConvertibleResponse}.
     * </p>
     */
    public RawResponse uploadDataset(DatasetProperties properties) throws IOException {
        Integer rawCode = 0;
        String rawBody = null;
        RawResponse rawResponse = new RawResponse();

        //create a new HttpClient and HttpPost
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(profile.getDatasetUploadAddress());

        //set headers
        post.addHeader(HttpHeaders.AUTHORIZATION, profile.getInstitutionKey());
        post.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

        //add json dataset properties to request
        StringEntity entity = new StringEntity(properties.toJson());
        post.setEntity(entity);

        try {
            //TODO: Implement timeout here
            rawResponse = responseConversion(client.execute(post));
        } catch (Exception ioException) {
            logger.error("#### uploadDataset(): could not execute post request");
            rawResponse.setResponseCode(100);
            rawResponse.setResponseBody(Instant.now() + ": CA-Pilot: could not execute post request for " +
                    profile.getDatasetUploadAddressProxy() + " ### " + ioException.getMessage());

            post.abort();
        }

        return rawResponse;
    }

    /**
     * <p>
     * Uploads files to CKAN Portal for a specific dataset based on specific properties. <br/>
     * The {@link RawResponse} object returned is evaluated with isValid() method.
     * </p>
     *
     * @param properties The properties object that holds the properties of the file to be uploaded.
     */
    public RawResponse uploadFiles(FileuploadProperties properties) {

        RawResponse rawResponse = new RawResponse();

        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(profile.getFileuploadAddress());
        post.addHeader("X-CKAN-API-Key", profile.getInstitutionKey());

        //setup object and file upload
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addTextBody("package_id", properties.getPackage_id());
        builder.addTextBody("url", properties.getUrl());
        builder.addTextBody("description", properties.getDescription());
        builder.addTextBody("author", properties.getAuthor());
        builder.addTextBody("title", properties.getTitle());
        builder.addTextBody("name", properties.getName());

        //create here the file attachment based on the byte[] field as stored in database.
        ByteArrayBody body = new ByteArrayBody(properties.getUpload(),
                ContentType.APPLICATION_XML, properties.getName() + ".xml");
        builder.addPart("upload", body);

        HttpEntity ckanEntity = builder.build();
        post.setEntity(ckanEntity);

        try {
            rawResponse = responseConversion(client.execute(post));
        } catch (IOException exception) {
            rawResponse.setResponseCode(101);
            rawResponse.setResponseBody(Instant.now() + ": CA-Pilot: could not execute UPLOAD_FILE post request for "
                    + properties.getName() + " ### " + exception.getMessage());
        }

        return rawResponse;
    }

    private RawResponse responseConversion(HttpResponse response) {
        RawResponse rawResponse = new RawResponse();
        OutputStream outputStream = new ByteArrayOutputStream();

        try {
            response.getEntity().writeTo(outputStream);
            rawResponse.setResponseBody(outputStream.toString());
            rawResponse.setResponseCode(response.getStatusLine().getStatusCode());
        } catch (IOException exception) {
            logger.error("##### responseConversion: Could not write to output stream the response; ");
            rawResponse.setResponseBody(exception.getMessage());
            rawResponse.setResponseCode(100);
        }

        return rawResponse;
    }

}
