./BigDatalog/bin/spark-submit --conf spark.kryoserializer.buffer.max=256 --master spark://${HOSTNAME}:7077  --class "org.apache.spark.examples.datalog.Experiments" BigDatalog/examples/target/scala-2.10/spark-examples-1.6.1-hadoop2.6.5.jar --file=hdfs://${HOSTNAME}:9000/experiment/flickr/edge_sssp.txt --program=32 --startvertex=0

