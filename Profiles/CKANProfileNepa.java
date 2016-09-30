package ro.nepa.site.CKAN.Profiles;

/**
 * NEPAProfile implementation for CKAN
 */
public final class CKANProfileNepa extends CKANProfile {
    /**
     * Institution key used for authorization on both dataset and file upload
     */
    private static final String INSTITUTION_KEY = "74f3db1c-fe03-425d-81aa-2edbe48f3a21";

    /**
     * Institution code which refers the institution that creates the dataset and uploads the files
     */
    private static final String INSTITUTION_CODE = "agentia-nationala-pentru-protectia-mediului";

    @Override
    public String getInstitutionCode() {
        return INSTITUTION_CODE;
    }

    @Override
    public String getInstitutionKey() {
        return INSTITUTION_KEY;
    }
}
