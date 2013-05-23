/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue.client;

import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.Mapper;

public class HibernateCollectionConverter extends CollectionConverter {
	HibernateCollectionConverter(Mapper mapper) {
		super(mapper);
	}

	public boolean canConvert(Class type) {
		return super.canConvert(type)
				|| org.hibernate.collection.PersistentList.class.equals(type)
				|| org.hibernate.collection.PersistentSet.class.equals(type);
	}
}
