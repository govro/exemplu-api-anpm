package ro.nepa.site.CKAN.Converters;

import ro.nepa.site.CKAN.*;
import ro.nepa.site.CKAN.Entities.ErrorResponse;
import ro.nepa.site.CKAN.Entities.RawResponse;
import ro.nepa.site.CKAN.Properties.DynamicLinkedProperties;
import ro.nepa.site.CKAN.Properties.ResponseDynamicLinkedProperties;
import ro.nepa.site.repositories.AbstractSearchableRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Evaluates the content of the JSON responseBody.
 */
public abstract class AbstractType<T extends RawResponse> implements ConvertibleResponse<T>, DynamicLinkedProperties {
    protected Class<T> domainClass;
    protected List<ResponseDynamicLinkedProperties> propertiesList = new ArrayList<>();

    public AbstractType() {
        Type genericSuperclass = getClass().getGenericSuperclass();

        while (!(genericSuperclass instanceof ParameterizedType)) {

            if (genericSuperclass instanceof Class) {
                throw new RuntimeException("unable to determine type");
            }

            //if called without typeParameter from source
            if (genericSuperclass instanceof AbstractSearchableRepository) {
                throw new RuntimeException("unable to determine type");
            }

            genericSuperclass = ((Class) genericSuperclass).getGenericSuperclass();
        }

        Type[] types = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        domainClass = (Class<T>) types[0];

        if (domainClass != ErrorResponse.class) {
            getPropertiesForThisType();
        }
    }

    @Override
    public List<ResponseDynamicLinkedProperties> getPropertiesForThisType() {
        for (ResponseDynamicLinkedProperties property : ResponseDynamicLinkedProperties.values()) {
            if ((property.getContainerClass() == domainClass) ||
                    (property.getContainerClass() == GenericResponse.class)) {

                propertiesList.add(property);
            }
        }

        return propertiesList;
    }
}
