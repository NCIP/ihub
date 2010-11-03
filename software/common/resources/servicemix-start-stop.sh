# START/ STOP / RESTART SERVICEMIX USING A SCRIPT.


#!/bin/sh
#
# SMX Init Script
#
SERVICEMIX_HOME=`dirname $0`


case "$1" in
start)
   $SERVICEMIX_HOME/bin/servicemix > /dev/null 2> "$@" &
   echo $! > $SERVICEMIX_HOME/servicemix.pid
   sleep 120
   echo "ServiceMix Started"
   exit 0
;;
stop)
   if [ -f $SERVICEMIX_HOME/servicemix.pid ]; then
   	read pid < $SERVICEMIX_HOME/servicemix.pid
   	kill -9 $pid
   	rm $SERVICEMIX_HOME/servicemix.pid
   	sleep 30
   	echo "ServiceMix Stopped"
   	exit 0
    else
       echo ""
       echo "ServiceMix PID file not found";
       exit 1
    fi

;;
restart)
   $0 stop
   $0 start
;;
*)
   echo "Usage: $0 (stop|start|restart)"
   exit 1
;;
esac

exit0