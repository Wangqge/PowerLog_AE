import random
#file1=open("edge.txt","r")
#file2=open("edge_sssp.txt","w");
#for line in file1:
#	file2.write(line.replace("\n","\t")+str(random.randint(1,9))+"\n")
#file1.close()
#file2.close()
file1=open("node.txt","r")
file2=open("node_bp.txt","w")
i=0;
for line in file1:
	if i==0:
		file2.write(line.replace("\n","\t")+"0.8\n")
	else:
	       	file2.write(line.replace("\n","\t")+"0.8\n")
	i=i+1
file1.close()
file2.close()
