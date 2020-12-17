cur_dir=$(cd "$(dirname "$0")"; pwd)
echo ${cur_dir}


#install openmpi-3.0.0
#wget https://download.open-mpi.org/release/open-mpi/v3.0/openmpi-3.0.0.tar.gz
tar xvzf ./openmpi-3.0.0.tar.gz
cd openmpi-3.0.0
./configure --prefix ${cur_dir}/openmpi --enable-mpi-java --enable-orterun-prefix-by-default
make -j4
sudo make install
