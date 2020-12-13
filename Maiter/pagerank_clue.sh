ALGORITHM=Pagerank
WORKERS=16
GRAPH=$SOCIALITE_PREFIX/ae_dataset/experiment/clue/maiter
RESULT=result/pagerank
NODES=12150976
SNAPSHOT=1
TERMTHRESH=0.0001
BUFMSG=1000
PORTION=1


./maiter  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --result_dir=$RESULT --num_nodes=$NODES --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > log


