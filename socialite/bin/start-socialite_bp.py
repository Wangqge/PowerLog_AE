#!/usr/bin/env python3
import os
import sys
from common import MASTER_HOSTNAME
from common import WORKER_NUM
from common import JAVA_HOME
from common import class_path
from common import WORKER_HOSTNAME_LIST
from common import SOCIALITE_PREFIX
print(WORKER_NUM)
USER_NAME=os.getenv('USER')
#MASTER_NAME=os.getenv('HOSTNAME')
#print (MASTER_NAME)
if len(sys.argv) != 2:
    print('Usage: script-name [start]/[kill] ')
    exit(1)

if sys.argv[1] == 'start':
    command_line = '''
   %s/bin/java -Xmx26G -ea \\
    -Dsocialite.worker.num=%d \\
    -Dsocialite.output.dir=%s/gen \\
    -Dsocialite.master=%s \\
    -Dsocialite.port=50100 \\
    -Dlog4j.configuration=file:%s/conf/log4j.properties \\
    -cp %s socialite.dist.worker.WorkerNode
    ''' % (JAVA_HOME,WORKER_NUM, SOCIALITE_PREFIX, MASTER_HOSTNAME, SOCIALITE_PREFIX, class_path)
    command_line = command_line.strip()

    for worker_host_name in WORKER_HOSTNAME_LIST:
        s = '''ssh -n %s@%s "sh -c 'cd %s; nohup %s > /dev/null 2>&1 &'"''' % (
        USER_NAME, worker_host_name, SOCIALITE_PREFIX, command_line)
        os.system(s)

    print("Works started!!!!!!")

    prog_args = 'dist %d %s%s%s %d %d'%(875713,'hdfs://',MASTER_HOSTNAME,':9000/experiment/google/edge.txt',2000,20)
    #prog_args = 'dist %d %s %d %d'%(int(sys.argv[2]),sys.argv[3],int(sys.argv[4]),int(sys.argv[5]))
    command_line = '''
    %s/bin/java -Xmx20G -ea \\
    -Dsocialite.worker.num=%d \\
    -Dsocialite.output.dir=%s/gen \\
    -Dsocialite.master=%s \\
    -Dsocialite.port=50100 \\
    -Dlog4j.configuration=file:%s/conf/log4j.properties \\
    -cp %s socialite.test.BP %s
    ''' % (JAVA_HOME,WORKER_NUM, SOCIALITE_PREFIX, MASTER_HOSTNAME, SOCIALITE_PREFIX, class_path, prog_args)
    command_line = command_line.strip()
    os.system(command_line)

else:
    for worker_host_name in WORKER_HOSTNAME_LIST:
        command_line = '''ssh -n %s@%s "%s/bin/jps|grep WorkerNode|awk '{print $1}'|xargs kill -9 2> /dev/null"''' % (
            USER_NAME, worker_host_name,JAVA_HOME)
        os.system(command_line)
