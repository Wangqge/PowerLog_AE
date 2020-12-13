ALGORITHM=Katz
WORKERS=16
GRAPH=$SOCIALITE_PREFIX/ae_dataset/experiment/arabic/maiter
RESULT=result/pagerank
NODES=22744080
SNAPSHOT=1
TERMTHRESH=0.001
BUFMSG=1000
PORTION=1
SOURCE=0
BETA=0.0017



./maiter  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --katz_source=$SOURCE --katz_beta=$BETA --result_dir=$RESULT --num_nodes=$NODES --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > log


