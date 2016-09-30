package ro.nepa.site.CKAN.Properties;

import ro.nepa.site.CKAN.Entities.DatasetResponse;
import ro.nepa.site.CKAN.Entities.FileuploadResponse;
import ro.nepa.site.CKAN.GenericResponse;

import java.util.Calendar;

/**
 * Properties enum.
 */
public enum ResponseDynamicLinkedProperties {
    ID {
        @Override
        public Class getPropertyClass() {
            return String.class;
        }

        @Override
        public Class getContainerClass() {
            return GenericResponse.class;
        }
    },
    METADATA_CREATED {
        @Override
        public Class getPropertyClass() {
            return Calendar.class;
        }

        @Override
        public Class getContainerClass() {
            return DatasetResponse.class;
        }
    },
    METADATA_MODIFIED {
        @Override
        public Class getPropertyClass() {
            return Calendar.class;
        }

        @Override
        public Class getContainerClass() {
            return DatasetResponse.class;
        }
    },
    CREATOR_USER_ID {
        @Override
        public Class getPropertyClass() {
            return String.class;
        }

        @Override
        public Class getContainerClass() {
            return DatasetResponse.class;
        }

    },
    NAME {
        @Override
        public Class getPropertyClass() {
            return String.class;
        }

        @Override
        public Class getContainerClass() {
            return GenericResponse.class;
        }
    },
    NOTES {
        @Override
        public Class getPropertyClass() {
            return String.class;
        }

        @Override
        public Class getContainerClass() {
            return DatasetResponse.class;
        }

    },
    TITLE {
        @Override
        public Class getPropertyClass() {
            return String.class;
        }

        @Override
        public Class getContainerClass() {
            return DatasetResponse.class;
        }

    },
    REVISION_ID {
        @Override
        public Class getPropertyClass() {
            return String.class;
        }

        @Override
        public Class getContainerClass() {
            return DatasetResponse.class;
        }

    },
    URL {
        @Override
        public Class getContainerClass() {
            return FileuploadResponse.class;
        }

        @Override
        public Class getPropertyClass() {
            return String.class;
        }
    },
    POSITION {
        @Override
        public Class getContainerClass() {
            return FileuploadResponse.class;
        }

        @Override
        public Class getPropertyClass() {
            return Integer.class;
        }
    }
    ;

    /**
     * Returns the {@link Class} of property
     * */
    public abstract Class getPropertyClass();

    /**
     * Returns the {@link Class} that contains the property
     * */
    public abstract Class getContainerClass();
}
