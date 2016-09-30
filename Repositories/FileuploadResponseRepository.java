package ro.nepa.site.CKAN.Repositories;

import org.springframework.data.repository.CrudRepository;
import ro.nepa.site.CKAN.Entities.FileuploadResponse;

/**
 * Default FileUploadResponseRepository implementation.
 */
public interface FileuploadResponseRepository extends CrudRepository<FileuploadResponse, Long> {

}
