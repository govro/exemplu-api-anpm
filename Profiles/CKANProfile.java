package ro.nepa.site.CKAN.Profiles;

/**
 * Abstract class declaration.
 */
public abstract class CKANProfile {

    private static String CKAN_WS_FILE_UPLOAD = "api/action/resource_create";

    private static String CKAN_WS_PROXY_URL_ROOT = "http://localhost:2000";

    private static String CKAN_WS_URL_ROOT = "http://data.gov.ro" ;

    private static String CKAN_WS_URL_DATASET_UPLOAD = "api/3/action/package_create";

    public String getDatasetUploadAddressProxy() {
        return CKAN_WS_PROXY_URL_ROOT + "/" + CKAN_WS_URL_DATASET_UPLOAD;
    }

    public String getDatasetUploadAddress() {
        return CKAN_WS_URL_ROOT + "/" + CKAN_WS_URL_DATASET_UPLOAD;
    }

    public String getFileuploadAddressProxy() {
        return CKAN_WS_PROXY_URL_ROOT + "/" + CKAN_WS_FILE_UPLOAD;
    }

    public String getFileuploadAddress() {
        return CKAN_WS_URL_ROOT + "/" + CKAN_WS_FILE_UPLOAD;
    }

    public abstract String getInstitutionCode();

    public abstract String getInstitutionKey();

}
