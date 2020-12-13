import random
file1=open("edge.txt","r")
#file3=open("node.txt","w")
#file4=open("node.csv","w")
file2=open("edge.csv","w")
froms=[0,0,0]
newedge=[]
newedges=[]
maxdata=0;
i=0
for line in file1:
    #froms[0]=(int(result[0]))
    #froms[1]=(int(result[1]))
    #maxdata=max(maxdata,froms[0])
    #maxdata=max(maxdata,froms[1])
    #froms[2]=random.randint(1, 10) 	
    file2.write(line.replace('\t',','))
	#	file3.write(str(froms[0])+"\t"+str(froms[1])+"\t"+str(random.randint(1,9))+"\n")



