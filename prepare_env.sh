#ubuntu 16.04
#Aliyun ECS clusters
#Or Amazon EC2 clusters
#A 16 node cluster with password-free ssh access


# variables for configure
MASTER_NAME=$HOSTNAME
HADOOP_PORT_NUMBER=9000
DOWNLOAD_HOST=http://hercules.cse.ohio-state.edu

###################################################

cur_dir=$(cd "$(dirname "$0")"; pwd) 
echo ${cur_dir}

bashfile=~/.bashrc
#bashfile="a.txt"
cur_dir1=$(cd "$(dirname "$0")"; pwd) 
#modify the bash file
echo -e '\n'>>${bashfile}
echo export JAVA_HOME=${cur_dir1}/jdk1.8.0_161  >>${bashfile}
echo export HADOOP_HOME=${cur_dir1}/hadoop-2.6.5 >>${bashfile}
echo export SOCIALITE_PREFIX=${cur_dir1} >>${bashfile}
echo export SPARK_HOME=${cur_dir1}/BigDatalog >>${bashfile}
echo export MYRIA_HOME=${cur_dir1}/myria >>${bashfile}
echo export MPI_HOME=${cur_dir1}/openmpi >>${bashfile}
echo 'export PATH=$JAVA_HOME/bin:$MPI_HOME/bin:$PATH' >>${bashfile}
echo 'export CLASSPATH=.$JAVA_HOME/lib:$JRE_HOME/lib'>>${bashfile}
echo 'export LD_LIBRARY_PATH=$MPI_HOME/lib:/usr/local/lib:/usr/lib'>>${bashfile}
source ${bashfile}


#install java
wget $DOWNLOAD_HOST/jdk-8u161-linux-x64.tar.gz
tar xvzf jdk-8u161-linux-x64.tar.gz
sudo apt update
sudo apt install scala y
sudo apt install git y
sudo apt install maven y
sudo apt install ant y

# download openmpi
if true; then
wget https://download.open-mpi.org/release/open-mpi/v3.0/openmpi-3.0.0.tar.gz
fi
./install_mpi.sh

#install hadoop
if true; then
wget archive.apache.org/dist/hadoop/common/hadoop-2.6.5/hadoop-2.6.5.tar.gz
fi
tar xvzf hadoop-2.6.5.tar.gz

#compiling PowerLog 

ant compile 

#compiling socialite
cd socialite 
ant compile
cd ..

#downloading Bigdatalog
if true; then
git clone https://github.com/ashkapsky/BigDatalog
fi
./prepare_BigDatalog.sh

#downloading Myria
if ture; then
git clone https://github.com/uwescience/myria
fi
./prepare_Myria.sh

#downloading PowerGraph
if true; then
git clone https://github.com/jegonzal/PowerGraph
fi



