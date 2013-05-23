/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
            package gov.nih.nci.clinicalconnector;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "thead_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Thead_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "XReference".equals(typeName)){
                   
                            return  _21090.org.iso.XReference.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "ScheduledArm".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.ScheduledArm.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "CCSystemError".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.CCSystemError.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "set_PostalAddressUse".equals(typeName)){
                   
                            return  _21090.org.iso.Set_PostalAddressUse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "PQR".equals(typeName)){
                   
                            return  _21090.org.iso.PQR.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "colgroup_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Colgroup_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "PerformedObservation".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.PerformedObservation.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "set_EntityNamePartQualifier".equals(typeName)){
                   
                            return  _21090.org.iso.Set_EntityNamePartQualifier.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "CD.CV".equals(typeName)){
                   
                            return  _21090.org.iso.CDCV.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "EntityNamePartQualifier".equals(typeName)){
                   
                            return  _21090.org.iso.EntityNamePartQualifier.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "AddressPartType".equals(typeName)){
                   
                            return  _21090.org.iso.AddressPartType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "CCBusinessError".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.CCBusinessError.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "scope_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Scope_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "td_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Td_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "CD".equals(typeName)){
                   
                            return  _21090.org.iso.CD.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "IVL_TS".equals(typeName)){
                   
                            return  _21090.org.iso.IVL_TS.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "CDGroup".equals(typeName)){
                   
                            return  _21090.org.iso.CDGroup.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "IVL_INT".equals(typeName)){
                   
                            return  _21090.org.iso.IVL_INT.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "Uid".equals(typeName)){
                   
                            return  _21090.org.iso.Uid.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "UncertaintyType".equals(typeName)){
                   
                            return  _21090.org.iso.UncertaintyType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type3".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type3.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "paragraph_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Paragraph_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type4".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type4.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "revised_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Revised_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "Message".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.Message.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "CD.CE".equals(typeName)){
                   
                            return  _21090.org.iso.CDCE.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "set_TelecommunicationAddressUse".equals(typeName)){
                   
                            return  _21090.org.iso.Set_TelecommunicationAddressUse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "scope_type1".equals(typeName)){
                   
                            return  _21090.org.iso.Scope_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "UpdateMode".equals(typeName)){
                   
                            return  _21090.org.iso.UpdateMode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type2".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "Compression".equals(typeName)){
                   
                            return  _21090.org.iso.Compression.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "set_CodingRationale".equals(typeName)){
                   
                            return  _21090.org.iso.Set_CodingRationale.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "QSET_INT".equals(typeName)){
                   
                            return  _21090.org.iso.QSET_INT.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "ENXP".equals(typeName)){
                   
                            return  _21090.org.iso.ENXP.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "tfoot_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Tfoot_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "set_EntityNameUse".equals(typeName)){
                   
                            return  _21090.org.iso.Set_EntityNameUse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "CR".equals(typeName)){
                   
                            return  _21090.org.iso.CR.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "EntityNameUse".equals(typeName)){
                   
                            return  _21090.org.iso.EntityNameUse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "StudyInvestigator".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.StudyInvestigator.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "listType_type0".equals(typeName)){
                   
                            return  _21090.org.iso.ListType_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type4".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type4.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "BL".equals(typeName)){
                   
                            return  _21090.org.iso.BL.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "tr_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Tr_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type7".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type7.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "Code".equals(typeName)){
                   
                            return  _21090.org.iso.Code.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "linkHtml_type0".equals(typeName)){
                   
                            return  _21090.org.iso.LinkHtml_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "EN.PN".equals(typeName)){
                   
                            return  _21090.org.iso.ENPN.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "th_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Th_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "frame_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Frame_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type7".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type7.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "StudyProtocol".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.StudyProtocol.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "ED".equals(typeName)){
                   
                            return  _21090.org.iso.ED.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "caption_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Caption_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "tbody_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Tbody_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type1".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "br_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Br_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "footnoteRef_type0".equals(typeName)){
                   
                            return  _21090.org.iso.FootnoteRef_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "renderMultiMedia_type0".equals(typeName)){
                   
                            return  _21090.org.iso.RenderMultiMedia_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "businessErrorCode".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.BusinessErrorCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "footnote_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Footnote_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type6".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type6.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "TEL".equals(typeName)){
                   
                            return  _21090.org.iso.TEL.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type6".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type6.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "PerformedClinicalResult".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.PerformedClinicalResult.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "IntegrityCheckAlgorithm".equals(typeName)){
                   
                            return  _21090.org.iso.IntegrityCheckAlgorithm.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "systemErrorCode".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.SystemErrorCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "CCBaseError".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.CCBaseError.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "EntityNamePartType".equals(typeName)){
                   
                            return  _21090.org.iso.EntityNamePartType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "PostalAddressUse".equals(typeName)){
                   
                            return  _21090.org.iso.PostalAddressUse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "ScheduledEpoch".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.ScheduledEpoch.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "EN".equals(typeName)){
                   
                            return  _21090.org.iso.EN.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "INT".equals(typeName)){
                   
                            return  _21090.org.iso.INT.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "content_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Content_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "IdentifierReliability".equals(typeName)){
                   
                            return  _21090.org.iso.IdentifierReliability.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type5".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type5.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "IdentifierScope".equals(typeName)){
                   
                            return  _21090.org.iso.IdentifierScope.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "CodingRationale".equals(typeName)){
                   
                            return  _21090.org.iso.CodingRationale.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "SC".equals(typeName)){
                   
                            return  _21090.org.iso.SC.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "StudySubject".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.StudySubject.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "PerformedSubjectMilestone".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.PerformedSubjectMilestone.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "AD".equals(typeName)){
                   
                            return  _21090.org.iso.AD.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "rules_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Rules_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "QTY".equals(typeName)){
                   
                            return  _21090.org.iso.QTY.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "item_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Item_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "CCDataValidationError".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.CCDataValidationError.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "ST".equals(typeName)){
                   
                            return  _21090.org.iso.ST.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "IVL_PQ".equals(typeName)){
                   
                            return  _21090.org.iso.IVL_PQ.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "StudyCoordinatingCenter".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.StudyCoordinatingCenter.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "col_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Col_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type3".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type3.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "NullFlavor".equals(typeName)){
                   
                            return  _21090.org.iso.NullFlavor.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "TS".equals(typeName)){
                   
                            return  _21090.org.iso.TS.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "Narrative".equals(typeName)){
                   
                            return  _21090.org.iso.Narrative.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "ED.Text".equals(typeName)){
                   
                            return  _21090.org.iso.EDText.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://clinicalconnector.nci.nih.gov".equals(namespaceURI) &&
                  "StudySite".equals(typeName)){
                   
                            return  gov.nih.nci.clinicalconnector.StudySite.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "QSET_PQ".equals(typeName)){
                   
                            return  _21090.org.iso.QSET_PQ.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type1".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "ANY".equals(typeName)){
                   
                            return  _21090.org.iso.ANY.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "URL".equals(typeName)){
                   
                            return  _21090.org.iso.URL.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "QSET_TS".equals(typeName)){
                   
                            return  _21090.org.iso.QSET_TS.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "PQ".equals(typeName)){
                   
                            return  _21090.org.iso.PQ.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "ADXP".equals(typeName)){
                   
                            return  _21090.org.iso.ADXP.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "align_type2".equals(typeName)){
                   
                            return  _21090.org.iso.Align_type2.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "TelecommunicationAddressUse".equals(typeName)){
                   
                            return  _21090.org.iso.TelecommunicationAddressUse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "list_type0".equals(typeName)){
                   
                            return  _21090.org.iso.List_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "HXIT".equals(typeName)){
                   
                            return  _21090.org.iso.HXIT.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "valign_type5".equals(typeName)){
                   
                            return  _21090.org.iso.Valign_type5.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "II".equals(typeName)){
                   
                            return  _21090.org.iso.II.Factory.parse(reader);
                        

                  }

              
                  if (
                  "uri:iso.org:21090".equals(namespaceURI) &&
                  "table_type0".equals(typeName)){
                   
                            return  _21090.org.iso.Table_type0.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    