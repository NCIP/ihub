/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

public class XScheduledEpoch {
    String description;
    String name;
    String scheduledStartDate;
    String typeCode;
    
    @Override
    public String toString() {
        return "description : "+this.description+
        "\nname : "+this.name+
        "\nscheduledStartDate : "+this.scheduledStartDate+
        "\ntypeCode : "+this.typeCode;
    }
}
