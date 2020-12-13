ALGORITHM=Adsorption
WORKERS=16
GRAPH=$SOCIALITE_PREFIX/ae_dataset/experiment/arabic/maiter
RESULT=result/pagerank	
NODES=22744080
SNAPSHOT=1
TERMTHRESH=0.001
BUFMSG=1000
PORTION=1
DAMPING=0.0004
START=1000000
#start=`date +%s`

./maiter --adsorption_starts=$START  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --result_dir=$RESULT --num_nodes=$NODES --adsorption_damping=$DAMPING --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > static.log

#end=`date +%s`
#time=`expr "$end" - "$start"`
