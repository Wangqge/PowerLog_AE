DATE=`date '+%Y-%m-%d %H:%M:%S'`
echo "----------------------------"$DATE >>./timefile
echo $i"th time starting"
for PARTITION in `cat partition.info`
do
 
	for DATA in `cat data`
	do
	echo $DATA" runtime">>./timefile
	sh adsorption.sh $DATA $PARTITION 

	sh con_component.sh $DATA $PARTITION

	sh pagerank.sh $DATA $PARTITION
	
        sh php.sh $DATA $PARTITION
       
        sh sssp.sh $DATA $PARTITION
	done
done
