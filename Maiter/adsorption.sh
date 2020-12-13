ALGORITHM=Adsorption
WORKERS=3
GRAPH=/home/wangqg/Maiter/input/pagerank
RESULT=result/pagerank	
NODES=875713
SNAPSHOT=1
TERMTHRESH=0.0001
BUFMSG=1000
PORTION=1
DAMPING=0.0003
START=10000
#start=`date +%s`

./maiter --adsorption_starts=$START  --runner=$ALGORITHM --workers=$WORKERS --graph_dir=$GRAPH --result_dir=$RESULT --num_nodes=$NODES --adsorption_damping=$DAMPING --snapshot_interval=$SNAPSHOT --portion=$PORTION --termcheck_threshold=$TERMTHRESH --bufmsg=$BUFMSG --v=0 > static.log

#end=`date +%s`
#time=`expr "$end" - "$start"`
