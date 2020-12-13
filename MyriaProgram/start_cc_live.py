from myria import *
import time

## Establish a default connection to Myria
MyriaRelation.DefaultConnection = MyriaConnection(rest_url='http://localhost:8753')

## Higher-level interaction via relation and query instances
start = time.time()
query = MyriaQuery.submit(
    """
E = load("hdfs://master:9000/experiment/live/edge.txt", csv(schema(src:int, dst:int),, delimiter="\t", skip=0));
V = [from E emit src as x] + [from E emit dst as x];
V = select distinct x from V;
do
  CC = [nid, MIN(cid) as cid] <-
    [from V emit V.x as nid, V.x as cid] +
    [from E, CC where E.src = CC.nid emit E.dst as nid, CC.cid];
until convergence sync pull_idb;
store(CC, CC_output);
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
relation = MyriaRelation(relation='CC_output')
print len(relation)

## Lower-level interaction via the REST API
connection = MyriaConnection(rest_url='http://localhost:8753')
datasets = connection.datasets()
