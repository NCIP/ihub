package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
    
public class MyNamespaceContext implements NamespaceContext
{
    public String getNamespaceURI(String prefix)
    {
        if (prefix.equals("ccts")) {
            return "gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain";
        }else if (prefix.equals("loadlabs")) {
        	 return "gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain.loadlabs";
        }
        else
            return XMLConstants.NULL_NS_URI;
    }
    
    public String getPrefix(String namespace)
    {
        if (namespace.equals("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain")){
        	  return "ccts";
        }else if (namespace.equals("gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain.loadlabs")){
        	 return "loadlabs";
        }
        else
            return null;
    }

    public Iterator getPrefixes(String namespace) {
        return null;
    }
}