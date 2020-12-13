./BigDatalog/bin/spark-submit --conf spark.kryoserializer.buffer.max=256 --master spark://${HOSTNAME}:7077 --class "org.apache.spark.examples.graphx.Analytics" BigDatalog/examples/target/scala-2.10/spark-examples-1.6.1-hadoop2.6.5.jar pagerank hdfs://${HOSTNAME}:9000/experiment/clue/edge.txt --numIter=34 --numEPart=16
