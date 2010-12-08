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
