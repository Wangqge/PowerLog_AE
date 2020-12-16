
cd PowerGraph 
./configure
cd release/toolkits/graph_analytics
make sssp
make connected_component

cd $SOCIALITE_PREFIX/PowerGraph/release/toolkits
$SOCIALITE_PREFIX/PowerGraph/scripts/mpirsync
cd $SOCIALITE_PREFIX/PowerGraph/deps/local
$SOCIALITE_PREFIX/PowerGraph/scripts/mpirsync
cd $SOCIALITE_PREFIX
