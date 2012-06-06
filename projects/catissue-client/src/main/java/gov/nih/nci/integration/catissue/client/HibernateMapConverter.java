package gov.nih.nci.integration.catissue.client;

import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.Mapper;

// CHECKSTYLE:OFF
public class HibernateMapConverter extends MapConverter {

    HibernateMapConverter(Mapper mapper) {
        super(mapper);
    }

    public boolean canConvert(Class type) {
        return super.canConvert(type) || org.hibernate.collection.PersistentMap.class.equals(type);
    }
}

// CHECKSTYLE:ON
