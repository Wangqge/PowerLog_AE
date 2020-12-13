ALGORITHM=Shortestpath
WORKERS=16
GRAPH=/home/wangqg/arabic/sssp
RESULT=result/sssp
SOURCE=100000
NODES=22744080
SNAPSHOT=1
TERMTHRESH=-1
BUFMSG=8000
PORTION=1.0

start=`date +%s`

./maiter  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --result_dir=$RESULT --shortestpath_source=$SOURCE --num_nodes=$NODES --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > static.log

