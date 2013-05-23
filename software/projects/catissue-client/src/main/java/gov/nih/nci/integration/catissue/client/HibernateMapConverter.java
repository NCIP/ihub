/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue.client;

import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.Mapper;

public class HibernateMapConverter extends MapConverter {

	HibernateMapConverter(Mapper mapper) {
		super(mapper);
	}

	public boolean canConvert(Class type) {
		return super.canConvert(type)
				|| org.hibernate.collection.PersistentMap.class.equals(type);
	}
}
