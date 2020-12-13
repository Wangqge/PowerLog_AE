ps -ef | grep DistEntry |awk '{print $2}' |xargs kill -9  
