This project includes the software and documentation related to the caBIG Integration Hub (iHub) - Mirth Connect product v1.8.2. This implementation modified the Mirth Connect v1.8.2 code to make it work with Jetty 6 instead of Jetty 5. This was done because Apache CXF was used to implement iHub Non-Grid interface and Apache CXF does not support Jetty 5. The plan was to release these minor code modifications to the Mirth community forum after the release of iHub-MirthConnect. However, this may not be necessary any longer since HTTPS interface can also be implemented using the SSL Tunneling approach.



Latest stable code:
The latest stable code is on the trunk. 

https://ncisvn.nci.nih.gov/svn/iHub/trunk

The planned scope for this implementation was as follows:

- Current (as of Suite v2.3) integration scenarios with C3PR, caAERS, LabViewer, PSC, C3D Connector and COPPA
- NOT IN SCOPE caTissue create specimen, and create participant API integration
- NOT IN SCOPE caAERS-AdEERS integration

Current status:

Development is complete, development testing is complete. 
Deploy script - BDA compatible deployment script is complete.
Documentation is in progress - architecture and design document is complete, Installation guide needs to be created.

NCI Suite QA team has not tested this yet.


