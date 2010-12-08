package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

public class XLoadLabsRequest {
	XPerformedClinicalResult[] performedClinicalResults;

    @Override
    public String toString() {
        return "performedClinicalResult : "+((this.performedClinicalResults != null)?this.performedClinicalResults.toString():"");
    }
}
