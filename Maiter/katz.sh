ALGORITHM=Pagerank
WORKERS=16
GRAPH=/home/wangqg/wiki/pagerank
RESULT=result/pagerank
NODES=12150976
SNAPSHOT=1
TERMTHRESH=10
BUFMSG=1000
PORTION=1
SOURCE=0
BETA=0.001



./maiter  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --katz_source=$SOURCE --katz_beta=$BETA --result_dir=$RESULT --num_nodes=$NODES --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > log


