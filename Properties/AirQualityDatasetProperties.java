package ro.nepa.site.CKAN.Properties;

import ro.nepa.site.CKAN.Profiles.CKANProfileNepa;

import java.util.UUID;

/**
 * Use this class for a default instance with dataset configuration needed when creating a new Airquality dataset
 */
public final class AirQualityDatasetProperties extends DatasetProperties {

    String _notes = "Setul de date contine masuratorile validate ale statiilor pentru monitorizarea calitatii aerului Datele sunt grupate in fisiere distincte, unice, care contin valorile tuturor parametrilor achizitionati in perioada unei luni calendaristice Structura fisierelor este unica";

    public AirQualityDatasetProperties(DatasetDescription description) {

        name = UUID.randomUUID().toString();
        notes = _notes;
        owner_org = new CKANProfileNepa().getInstitutionCode();
        title = description.toString();
        version = 1;

        //this does not work!
        //tags = description.getTags();

    }

}
