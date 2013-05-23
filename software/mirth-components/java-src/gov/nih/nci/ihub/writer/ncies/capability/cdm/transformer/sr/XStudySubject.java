/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

public class XStudySubject {
    String birthDate;
    String identifier;
    String identifierName;
    String firstName;
    String lastName;
    String raceCode;
    String sexCode;
    String studyIdentifier;
    String studyIdentifierName;
    String studySiteIdentifier;
    String studySiteIdentifierName;
    
    @Override
    public String toString() {
        return "birthDate : "+this.birthDate+
        "\nidentifier : "+this.identifier+
        "\nidentifierName : "+this.identifierName+
        "\nfirstName : "+this.firstName+
        "\nlastName : "+this.lastName+
        "\nraceCode : "+this.raceCode+
        "\nsexCode : "+this.sexCode+
        "\nstudyIdentifier : "+this.studyIdentifier+
        "\nstudyIdentifierName : "+this.studyIdentifierName+
        "\nstudySiteIdentifier : "+this.studySiteIdentifier+
        "\nstudySiteIdentifierName : "+this.studySiteIdentifierName;
    }
}
