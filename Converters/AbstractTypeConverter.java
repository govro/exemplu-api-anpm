package ro.nepa.site.CKAN.Converters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import ro.nepa.site.CKAN.Entities.ErrorResponse;
import ro.nepa.site.CKAN.Entities.RawResponse;
import ro.nepa.site.CKAN.Properties.ResponseDynamicLinkedProperties;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * Base class for converting CKAN Response. The class converts to the type passed as parameter type in generic signature. <br/>
 * Example: DatasetResponse d = new DatasetResponseConverter().convert(response)
 * </p>
 */
public abstract class AbstractTypeConverter<T extends RawResponse>
        extends AbstractType<T> implements Serializable {


    final String ISO_864_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";


    @Override
    public T convert(RawResponse response) {

        T responseType = null;
        try {
            responseType = domainClass.newInstance();
            responseType.setResponseBody(response.getResponseBody());
            responseType.setResponseCode(response.getResponseCode());
        } catch (Exception e) {
            //ignore
        }

        //set basic fields


        //if it is not an error, set the fields according to json
        if (!(domainClass == ErrorResponse.class)) {

            try {
                JsonParser parser = new JsonFactory().createParser(response.getResponseBody());

                while (!parser.isClosed()) {
                    JsonToken token = parser.nextToken();
                    Object value = null;

                    if (JsonToken.FIELD_NAME.equals(token)) {
                        String fieldName = parser.getCurrentName();

                        parser.nextToken();

                        for (ResponseDynamicLinkedProperties property : propertiesList) {

                            if (property.toString().equalsIgnoreCase(fieldName)) {

                                if (property.getPropertyClass() == Boolean.class) {
                                    value = parser.getValueAsBoolean();

                                } else if (property.getPropertyClass() == String.class) {
                                    value = parser.getValueAsString();

                                } else if (property.getPropertyClass() == Calendar.class) {
                                    value = convertDate(parser.getValueAsString());

                                } else if (property.getPropertyClass() == Integer.class) {
                                    value = parser.getIntValue();
                                }

                                setProperty(responseType, property.toString().toLowerCase(), value);

                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                //ignore
            }
        }

        return responseType;

    }

    private boolean setProperty(Object object, String fieldName, Object value) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> responseObject = Class.forName(domainClass.getCanonicalName());
        Field field = responseObject.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);

        return false;
    }

    private Calendar convertDate(String source) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(ISO_864_FULL_FORMAT);
        Date date = format.parse(source);

        Calendar c = new GregorianCalendar();
        c.setTime(date);

        return c;

    }
}
