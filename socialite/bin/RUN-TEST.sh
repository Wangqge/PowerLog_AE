#!/usr/bin/env bash
BIN=`dirname "$0"`
BIN=`cd "$BIN"; pwd`

. ${BIN}/common.sh
echo ${JAR_PATH}
function run_single(){

    ${BIN}/kill-all.sh ${MACHINES}

    java -Xmx8G \
    -Dsocialite.output.dir=${SOCIALITE_PREFIX}/gen \
    -Dlog4j.configuration=file:${SOCIALITE_PREFIX}/conf/log4j.properties \
    -cp ${CODE_CLASSPATH}:${JAR_PATH} \
    socialite.test.CC $1 $2 $3 $4
}

echo $1
echo $2
echo $3
echo $4
if [ "$#" == "4" ]; then
    CODE_CLASSPATH=${SOCIALITE_PREFIX}/classes/socialite.jar 
echo "in"
 run_single $1 $2 $3 $4
else
    echo "please specify [Datalog_Program]"
    exit 1
fi
