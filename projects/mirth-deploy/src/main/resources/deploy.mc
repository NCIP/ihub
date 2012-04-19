channel stop *
channel undeploy *

channel remove TranscendInboundChannel
channel remove CaCISChannel
channel remove MessageBroadcasterChannel

importcodetemplates "@{mirth.files.base.dir}/codetemplates/ihub-mc-code-templates.xml" force

import "@{mirth.files.base.dir}/channels/TranscendInboundChannel.xml" force
import "@{mirth.files.base.dir}/channels/CaCISChannel.xml"  force
import "@{mirth.files.base.dir}/channels/MessageBroadcasterChannel.xml" force

channel list