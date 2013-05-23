/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

public class XPerformedClinicalResult {
    String asCollectedIndicator;
    String comment;
    String confidentialityCode;
    String numericalResult;
    String numericalResultPrecision;
    String numericalResultUnit;
    String referenceRangeHigh;
    String referenceRangeLow;
    String referenceRangeComment;
    String reportedDate;
    String reportedResultStatusCode;
    String textResult;
    String uncertaintyCode;
    XPerformedObservation performedObservation;
    
    @Override
    public String toString() {
        return "XPerformedClinicalResult: asCollectedIndicator : "+this.asCollectedIndicator+
        "\ncomment : "+this.comment+
        "\nconfidentialityCode : "+this.confidentialityCode+
        "\nnumericalResult : "+this.numericalResult+
        "\nreferenceRangeHigh : "+this.referenceRangeHigh+
        "\nreferenceRangeHigh : "+this.referenceRangeHigh+
        "\nreferenceRangeLow : "+this.referenceRangeLow+
        "\nreferenceRangeComment : "+this.referenceRangeComment+
        "\nreportedDate : "+this.reportedDate+
        "\nreportedResultStatusCode : "+this.reportedResultStatusCode+
        "\ntextResult : "+this.textResult+
        "\nuncertaintyCode : "+this.uncertaintyCode+
        "\nperformedObservation : "+this.performedObservation;
    }
}
