from myria import *
import time

## Establish a default connection to Myria
MyriaRelation.DefaultConnection = MyriaConnection(rest_url='http://localhost:8753')

## Higher-level interaction via relation and query instances
start = time.time()
query = MyriaQuery.submit(
    """
E = load("hdfs://master:9000/experiment/orkut/edge_sssp.txt", csv(schema(src:int, dst:int, weight:int), delimiter="\t"));
INIT_SSSP = load("hdfs://master:9000/experiment/orkut/node_sssp.txt", csv(schema(src:int, dist:int), delimiter="\t"));
do
  SSSP = [nid, MIN(dist) as dist] <-
    [from INIT_SSSP emit INIT_SSSP.src as nid, INIT_SSSP.dist as dist] +
    [from E, SSSP where E.src = SSSP.nid emit E.dst as nid, SSSP.dist + E.weight];
until convergence pull_idb;
store(SSSP, SSSP_output);
""")
end = time.time()
print("myria execution time:%.2f second"%(end-start))
# Download relation and convert it to JSON
json = query.to_dict()
print (json)
# ... or download to a Pandas Dataframe
dataframe = query.to_dataframe()

# ... or download to a Numpy array
#dataframe = query.to_dataframe().as_matrix()

## Access an already-stored relation
relation = MyriaRelation(relation='SSSP_output')
print len(relation)

## Lower-level interaction via the REST API
connection = MyriaConnection(rest_url='http://localhost:8753')
datasets = connection.datasets()
