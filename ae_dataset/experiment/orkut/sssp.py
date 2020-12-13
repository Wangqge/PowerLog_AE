import random
file1=open("edge.txt","r")
file3=open("node.txt","w")
file4=open("node_sssp.txt","w")
file2=open("edge_sssp.txt","w")
froms=[0,0,0]
newedge=[]
newedges=[]
maxdata=0;
i=0
for line in file1:
    result=line.split()
    froms[0]=(int(result[0]))
    froms[1]=(int(result[1]))
    maxdata=max(maxdata,froms[0])
    maxdata=max(maxdata,froms[1])
    froms[2]=random.randint(1, 10) 	
    file2.write(str(froms[0])+"	"+str(froms[1])+"	"+str(froms[2])+"\n")
	#	file3.write(str(froms[0])+"\t"+str(froms[1])+"\t"+str(random.randint(1,9))+"\n")
for j in range(0,maxdata):
	file3.write(str(j)+"\n")
	if j==0:
		file4.write(str(j)+"\t"+str(0)+"\n")
	else:
		file4.write(str(j)+"\t"+str(2147483647)+"\n")



