package ro.nepa.site.CKAN.Converters;

import ro.nepa.site.CKAN.Converters.AbstractType;
import ro.nepa.site.CKAN.Entities.RawResponse;

/**
 * Interface used for converting the responseBody received from the CKAN portal to a structured object of type
 * Created by AlexandruG on 01/06/16.
 */


public interface ConvertibleResponse<T extends RawResponse> {

    /**
     * <p>Generic method for converting the CKAN JSON responseBody to a {@link AbstractType}.</p>
     * */
    T convert(RawResponse response) throws Exception;

}
