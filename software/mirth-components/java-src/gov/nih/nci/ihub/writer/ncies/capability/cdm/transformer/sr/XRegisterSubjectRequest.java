package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

public class XRegisterSubjectRequest {
    XPerformedSubjectMilestone performedSubjectMilestone;
    XStudySubject studySubject;
    XScheduledEpoch scheduledEpoch;
    XScheduledArm scheduledArm;
    
    @Override
    public String toString() {
        return "performedSubjectMilestone : "+((this.performedSubjectMilestone != null)?this.performedSubjectMilestone.toString():"") +
        "\nstudySubject : "+((this.studySubject != null)?this.studySubject.toString():"") + 
        "\nscheduledEpoch : "+((this.scheduledEpoch != null)?this.scheduledEpoch.toString():"") + 
        "\nscheduledArm : "+((this.scheduledArm != null)?this.scheduledArm.toString():"");
    }
}