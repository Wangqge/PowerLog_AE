from myria import *
import time

## Establish a default connection to Myria
MyriaRelation.DefaultConnection = MyriaConnection(rest_url='http://localhost:8753')

## Higher-level interaction via relation and query instances
start = time.time()
query = MyriaQuery.submit(
    """
alpha = [.8];
COUNTER = [0];
MAX_ITER = [34];

Edge = load("hdfs://master:9000/experiment/live/edge.txt", csv(schema(src:int, dst:int), delimiter="\t"));
Vertex = [from Edge emit src as x] + [from Edge emit dst as x];
Vertex = select distinct x as id from Vertex;

N = [FROM Vertex EMIT COUNT(id) AS val];
min_rank = [(1 - *alpha) / *N];

OutDegree = [FROM Edge EMIT Edge.src AS id, COUNT(Edge.dst) AS cnt];
PageRank = [FROM Vertex EMIT Vertex.id AS id, 1.0 / *N AS rank];
--sink(OutDegree);
--sink(PageRank);
DO
    -- Calculate each node's outbound page rank contribution
    PrOut = [FROM PageRank, OutDegree WHERE PageRank.id == OutDegree.id
             EMIT PageRank.id AS id, PageRank.rank / OutDegree.cnt AS out_rank];

    -- Compute the inbound summands for each node
    Summand = [FROM Vertex, Edge, PrOut
                WHERE Edge.dst == Vertex.id AND Edge.src == PrOut.id
                EMIT Vertex.id AS id, PrOut.out_rank AS summand];

    -- Sum up the summands; adjust by alpha
    NewPageRank = [FROM Summand EMIT id AS id,
                   *min_rank + *alpha * SUM(Summand.summand) AS rank];
    Continue = [*COUNTER < *MAX_ITER];
    COUNTER = [*COUNTER + 1];
    PageRank = NewPageRank;
WHILE Continue;
STORE(PageRank, PR_OUTPUT);
--sink(PageRank);
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
relation = MyriaRelation(relation='PR_OUTPUT')
print len(relation)

## Lower-level interaction via the REST API
connection = MyriaConnection(rest_url='http://localhost:8753')
datasets = connection.datasets()
