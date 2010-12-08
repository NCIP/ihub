package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

public class XScheduledArm {
    String description;
    String name;
    String typeCode;
    
    @Override
    public String toString() {
        return "description : "+this.description+
        "\nname : "+this.name+
        "\ntypeCode : "+this.typeCode;
    }
}
