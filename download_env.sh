#ubuntu 16.04
#Aliyun ECS clusters
#Or Amazon EC2 clusters
#A 16 node cluster with password-free ssh access


# variables for configure
DOWNLOAD_HOST=http://hercules.cse.ohio-state.edu

###################################################

cur_dir=$(cd "$(dirname "$0")"; pwd) 
echo ${cur_dir}

bashfile=~/.bashrc
#bashfile="a.txt"
cur_dir1=$(cd "$(dirname "$0")"; pwd) 

#install java
wget $DOWNLOAD_HOST/jdk-8u161-linux-x64.tar.gz
sudo apt update
sudo apt install scala -y
sudo apt install git -y
sudo apt install maven -y
sudo apt install ant -y

# download openmpi
if true; then
wget https://download.open-mpi.org/release/open-mpi/v3.0/openmpi-3.0.0.tar.gz
fi

#install hadoop
if true; then
wget archive.apache.org/dist/hadoop/common/hadoop-2.6.5/hadoop-2.6.5.tar.gz
fi

#downloading Bigdatalog
if true; then
git clone https://github.com/ashkapsky/BigDatalog
fi

#downloading Myria
if ture; then
git clone https://github.com/uwescience/myria
fi

#downloading PowerGraph
if true; then
git clone https://github.com/jegonzal/PowerGraph
fi



