
ALGORITHM=con_component
WORKERS=16
GRAPH=/home/wangqg/wiki/pagerank
RESULT=result/CC
NODES=12150976
SNAPSHOT=1
TERMTHRESH=0
BUFMSG=8000
PORTION=1

start=`date +%s`

./maiter  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --result_dir=$RESULT --num_nodes=$NODES --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > static.log

end=`date +%s`
time=`expr "$end" - "$start"`
