package ro.nepa.site.CKAN.Entities;

import ro.nepa.site.CKAN.CKANSession;
import ro.nepa.site.CKAN.Converters.AbstractTypeConverter;

import javax.persistence.*;

/**
 * <p>
 * Base class for responses sent by the CKAN Portal. Use this class in combination with {@link CKANSession} to structure the response. <br/>
 * Conversion to specific types of responses is made by invoking the {@link AbstractTypeConverter} generic class
 * </p>
 */

@MappedSuperclass
public class RawResponse {


    protected String responseBody;
    protected Integer responseCode;

    @Lob
    @Basic
    @Column(name = "ResponseBody")
    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Basic
    @Column(name = "ResponseCode")
    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    @Transient
    public boolean isValid() {

        return responseCode == 200;

    }
}
