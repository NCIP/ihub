/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

public class XLoadLabsRequest {
	XPerformedClinicalResult[] performedClinicalResults;

    @Override
    public String toString() {
        return "performedClinicalResult : "+((this.performedClinicalResults != null)?this.performedClinicalResults.toString():"");
    }
}
