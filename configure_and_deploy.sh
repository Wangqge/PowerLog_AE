#ubuntu 16.04
#Aliyun ECS clusters
#Or Amazon EC2 clusters
#A 16 node cluster with password-free ssh access



cur_dir=$(cd "$(dirname "$0")"; pwd) 
echo ${cur_dir}

# variables for configure
MASTER_NAME=$HOSTNAME        
HADOOP_PORT_NUMBER=9000
NUMBER_OF_WORKERS=2
WORKERS[0]="hadoop001"
WORKERS[1]="hadoop002"
#WORKERS[2]="W3"
#WORKERS[3]="W4"
#WORKERS[4]="W5"
#WORKERS[5]="W6"
#WORKERS[6]="W7"
#WORKERS[7]="W8"
#WORKERS[8]="W9"
#WORKERS[9]="W10"
#WORKERS[10]="W11"
#WORKERS[11]="W12"
#WORKERS[12]="W13"
#WORKERS[13]="W14"
#WORKERS[14]="W15"

###################################################
JAVA_HOME=${cur_dir}/jdk1.8.0_161
#hadoop_configuration


#myria_env
MY_MYRIA_HOME=${cur_dir}/hadoop-2.6.5
cp -r ./prepare_configure_file/myria_conf/* ${MY_MYRIA_HOME}/myriadeploy/

#HADOOP
MY_HADOOP_HOME=${cur_dir}/hadoop-2.6.5
hadoop_worker_file=${MY_HADOOP_HOME}/etc/hadoop/slaves_1

#if false;then
#configure
cd ./prepare_configure_file/hadoop_conf/etc/hadoop
./prepare_hadoop_conf.sh ${MASTER_NAME} ${HADOOP_PORT_NUMBER} ${MY_HADOOP_HOME} ${JAVA_HOME}
cd ../../../..
cp -r ./prepare_configure_file/hadoop_conf/etc/hadoop/* ${MY_HADOOP_HOME}/etc/hadoop/

#slaves
for((i=0;i<${NUMBER_OF_WORKERS};i++));
do
echo ${WORKERS[$i]} >>${hadoop_worker_file}
done
mv ./hadoop-2.6.5/etc/hadoop/slaves_1 ./hadoop-2.6.5/etc/hadoop/slaves


#bigdatalog_env

#conf
MY_BDL_HOME=${cur_dir}/BigDatalog
bdl_worker_file=${MY_BDL_HOME}/conf/slaves_1

cd ./prepare_configure_file/spark_conf/
./prepare_spark_conf.sh ${MASTER_NAME} ${MY_HADOOP_HOME} ${JAVA_HOME}
cd ../../
cp -r ./prepare_configure_file/spark_conf/* ${MY_BDL_HOME}/conf

#slaves
for((i=0;i<${NUMBER_OF_WORKERS};i++));
do
echo ${WORKERS[$i]} >>${bdl_worker_file}
done
mv ${MY_BDL_HOME}/conf/slaves_1 ${MY_BDL_HOME}/conf/slaves


#prepare powerlog
MY_POWERLOG_HOME=${cur_dir}
powerlog_worker_file=${MY_POWERLOG_HOME}/conf/machines_1
echo ${MASTER_NAME} slots=1 >>${powerlog_worker_file}
for((i=0;i<${NUMBER_OF_WORKERS};i++));  
do
echo ${WORKERS[$i]} slots=1 >>${powerlog_worker_file}
done  
mv "./conf/machines_1" "./conf/machines"




#prepare socialite 
MY_SOCIALITE_HOME=${cur_dir}
socialite_worker_file=${MY_SOCIALITE_HOME}/socialite/conf/machines_1
socialite_slave_file=${MY_SOCIALITE_HOME}/socialite/conf/slaves_1

echo ${MASTER_NAME} slots=1 >>${socialite_worker_file}
for((i=0;i<${NUMBER_OF_WORKERS};i++));  
do   
echo ${WORKERS[$i]} slots=1 >>${socialite_worker_file}
echo ${WORKERS[$i]}>>${socialite_slave_file}
done  
mv "./socialite/conf/machines_1" "./socialite/conf/machines"
mv "./socialite/conf/slaves_1" "./socialite/conf/slaves"



#prepare powergraph
MY_POWERGRAPH_HOME=~
powergraph_worker_file=${MY_POWERGRAPH_HOME}/machines_1
echo ${MASTER_NAME} >>${powergraph_worker_file}
for((i=0;i<${NUMBER_OF_WORKERS};i++));
do
echo ${WORKERS[$i]} >>${powergraph_worker_file}
done
mv ${MY_POWERGRAPH_HOME}/machines_1 ${MY_POWERGRAPH_HOME}/machines



#prepare maiter
MY_MAITER_HOME=${cur_dir}/Maiter
maiter_worker_file=${MY_MAITER_HOME}/conf/mpi-cluster1
echo ${MASTER_NAME} slots=1 >>${maiter_worker_file}
for((i=0;i<${NUMBER_OF_WORKERS};i++));
do
echo ${WORKERS[$i]} slots=1 >>${maiter_worker_file}
done
mv ${MY_MAITER_HOME}/conf/mpi-cluster1 ${MY_MAITER_HOME}/conf/mpi-cluster



if true; then
#copy all things to all workers
for((i=0;i<${NUMBER_OF_WORKERS};i++));  
do
ssh ${USER}@${WORKERS[$i]} "mkdir -p ${cur_dir}"
scp -r ./*  ${USER}@${WORKERS[$i]}:${cur_dir}/
done
fi

if true;then
# copy bash file to all worker
for((i=0;i<${NUMBER_OF_WORKERS};i++));  
do
scp -r ~/.bashrc ${USER}@${WORKERS[$i]}:/home/${USER}
ssh ${USER}@${WORKERS[$i]} "source ~/.bashrc"
done
fi
#fi


