#!/usr/bin/env python3
import os
import sys
from common import USER_NAME
from common import WORKER_HOSTNAME_LIST
from common import SOCIALITE_PREFIX

if len(sys.argv) != 2:
    print('Usage: script-name [install]/[update]')
    exit(1)

if sys.argv[1] == 'install':
    for worker_host_name in WORKER_HOSTNAME_LIST:
        command_line = 'scp -r %s %s@%s:/home/%s/' % (SOCIALITE_PREFIX, USER_NAME, worker_host_name, USER_NAME)
        os.system(command_line)
else:
    for worker_host_name in WORKER_HOSTNAME_LIST:
        command_line = 'scp -r %s %s@%s:/home/%s/socialite/' % (
        SOCIALITE_PREFIX + "/out", USER_NAME, worker_host_name, USER_NAME)
        os.system(command_line)
        # command_line = 'scp -r %s %s@%s:/home/%s/socialite/classes' % (
        # SOCIALITE_PREFIX + "/classes", USER_NAME, worker_host_name, USER_NAME)
        # os.system(command_line)
