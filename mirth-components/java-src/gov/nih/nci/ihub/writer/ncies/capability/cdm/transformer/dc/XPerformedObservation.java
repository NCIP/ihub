package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

public class XPerformedObservation {
    String activityNameCode;
    String studyProtocolIdentifier;
    String studyProtocolIdentifierName;
    String studyProtocolIdentifierRoot;
    String studySubjectIdentifier;
    String studySubjectIdentifierName;
    String studySubjectIdentifierRoot;
    
    @Override
    public String toString() {
        return "XPerformedObservation: activityNameCode : "+this.activityNameCode+
        "\nstudyProtocolIdentifier : "+this.studyProtocolIdentifier+
        "\nstudyProtocolIdentifierName : "+this.studyProtocolIdentifierName+
        "\nstudySubjectIdentifier : "+this.studySubjectIdentifier+
        "\nstudySubjectIdentifierName : "+this.studySubjectIdentifierName;
    }
}
