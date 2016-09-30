package ro.nepa.site.CKAN.Repositories;

import org.springframework.data.repository.CrudRepository;
import ro.nepa.site.CKAN.Entities.ErrorResponse;

/**
 * Default ErrorResponseRepository implementation
 */
public interface ErrorResponseRepository extends CrudRepository<ErrorResponse, Long> {

}
