#!/bin/sh

#
# Import gme table data into a gme database on another server
#



GME_DATABASE_PREFIX=@database.gme.name@
GME_DATABASE_USER=@database.gme.username@
GME_DATABASE_PASSWORD=@database.gme.password@
GME_DATABASE_HOST=@database.gme.host@
GME_DATABASE_PORT=@database.gme.port@
MY_SQL_HOME="@database.mysql.home@"
GME_SERVICE_URL=@gme.service.url@



importFileName=gmeDBExport.tar
oldservicename=http://localhost:8090/wsrf/services/cagrid/GlobalModelExchange

export PATH=${MY_SQL_HOME}/bin:${PATH}

tar -xvf ${importFileName}

databases="GME_REGISTRY GME_SCHEMA_STORE GME_SCHEMA_CACHE"

for database in $databases ; do

echo Importing gme database table data into ${database}

gunzip ${database}.sql.gz

mysql -u ${GME_DATABASE_USER} --password=${GME_DATABASE_PASSWORD} --host=${GME_DATABASE_HOST} --port=${GME_DATABASE_PORT} ${GME_DATABASE_PREFIX}_${database} < ${database}.sql

rm -fr ${database}.sql.gz
rm -fr ${database}.sql

done

echo Finished Importing databases

database="${GME_DATABASE_PREFIX}_GME_REGISTRY"


echo changing hostname to ${GME_SERVICE_URL}

echo "use ${database}; update NAMESPACES set SERVICE_ID='${GME_SERVICE_URL}' where SERVICE_id='${oldservicename}';" | mysql -u ${GME_DATABASE_USER} --password=${GME_DATABASE_PASSWORD} --host=${GME_DATABASE_HOST} --port=${GME_DATABASE_PORT}

echo Finished Modifying Databases

exit 