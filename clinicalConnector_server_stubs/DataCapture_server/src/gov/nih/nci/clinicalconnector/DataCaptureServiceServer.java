/**
 * Server class simulates CDMS server business logic.
 * 
 * @author John Chen
 */
package gov.nih.nci.clinicalconnector;

import _21090.org.iso.BL;
import _21090.org.iso.II;
import _21090.org.iso.PQ;

/**
 * Server has business logic.
 */
public class DataCaptureServiceServer extends ServerBase {

    /**
     * Rolls back load labs.
     * 
     * @param request RollbackLoadLabsRequest 
     */
    public RollbackLoadLabsResponse rollbackLoadLabs(RollbackLoadLabsRequest request) {

        log("########### enter rollbackLoadLabs()");
        
        PerformedClinicalResult[] prs = request.getPerformedClinicalResults();
        if (prs != null) {
        	int total = prs.length;
        	log("received data set size: " + total);
        	
        	for (int i = 0; i < total; i++) {
        		PerformedClinicalResult pr = prs[i];
                
                II identifier = pr.getPerformedObservation().getStudySubjectIdentifier();
                String identifierName = identifier.getIdentifierName();
                String idExtension = identifier.getExtension();
                log("received subject identifier: " + identifierName + " - " + idExtension);
                
                PQ numericalResult = pr.getNumericalResult();
                log("received     value: " + numericalResult.getValue());
                log("received precision: " + numericalResult.getPrecision());
                log("received      unit: " + numericalResult.getUnit().getCode());
                
        	}
        }
        
        RollbackLoadLabsResponse response = new RollbackLoadLabsResponse();
        
        BL indicatorBL = new BL();
        indicatorBL.setValue(true);
        response.setIndicator(indicatorBL);
        
        log("########### leave rollbackLoadLabs()");
        log("");
        log("");
        return response;
    }

    /**
     * Loads labs.
     * 
     * @param request LoadLabsRequest 
     */
    public LoadLabsResponse loadLabs(LoadLabsRequest request) {

        log("########### enter loadLabs()");
        
        PerformedClinicalResult[] prs = request.getPerformedClinicalResults();
        if (prs != null) {
        	int total = prs.length;
        	log("received data set size: " + total);
        	
        	for (int i = 0; i < total; i++) {
        		PerformedClinicalResult pr = prs[i];
                
                II identifier = pr.getPerformedObservation().getStudySubjectIdentifier();
                String identifierName = identifier.getIdentifierName();
                String idExtension = identifier.getExtension();
                log("received subject identifier: " + identifierName + " - " + idExtension);
                
                PQ numericalResult = pr.getNumericalResult();
                log("received     value: " + numericalResult.getValue());
                log("received precision: " + numericalResult.getPrecision());
                log("received      unit: " + numericalResult.getUnit().getCode());
                
        	}
        }
        
        LoadLabsResponse response = new LoadLabsResponse();
        
        BL indicatorBL = new BL();
        indicatorBL.setValue(true);
        response.setIndicator(indicatorBL);
        
        log("########### leave loadLabs()");
        log("");
        log("");
        return response;
    }
}