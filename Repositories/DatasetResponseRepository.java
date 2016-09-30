package ro.nepa.site.CKAN.Repositories;

import org.springframework.data.repository.CrudRepository;
import ro.nepa.site.CKAN.Entities.DatasetResponse;
import ro.nepa.site.entities.Station;

/**
 * Default DatasetRepository.
 */
public interface DatasetResponseRepository extends CrudRepository<DatasetResponse,Long> {
    DatasetResponse findByStation(Station station);

    long countByStation(Station station);
}
