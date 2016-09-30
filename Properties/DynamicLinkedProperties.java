package ro.nepa.site.CKAN.Properties;

import ro.nepa.site.CKAN.GenericResponse;

import java.util.List;

/**
 * <p><Detachable getPropertiesForThisType are CKAN responseBody getPropertiesForThisType that are included in
 * {@link ResponseDynamicLinkedProperties} enum. This interface is applicable specially
 * for {@link GenericResponse} objects.
 *
 * </p>
 */
public interface DynamicLinkedProperties {

    /**
     * <p>Retrieves the list of {@link ResponseDynamicLinkedProperties}</p>
     * */
    List<ResponseDynamicLinkedProperties> getPropertiesForThisType();
}
