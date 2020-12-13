import random
import os
paths=os.getcwd()
os.makedirs(paths+"/maiter")
file1=open("edge.txt","r")
partition_size=16
file2=[]
file3=[]
for i in range(0,partition_size):
        file2.append(open("maiter/part"+str(i),"w"))
#       file3.append(open("maiter_sssp.txt"+str(i),"a"))
froms=[0,0,0]

i=0
adj=[]
src=0
for line in file1:
    if src!=froms[0]:
        file2[src%partition_size].write(str(src)+"\t")
#       file3[src%partition_size].write(str(src)+"\t")
        for j in range(0,len(adj)-1):
                file2[src%partition_size].write(str(adj[j])+" ")
#               file3[src%partition_size].write(str(adj[j])+"\t"+str(froms[2])+"\t")
        file2[src%partition_size].write("\n")
#       file3[src%partition_size].write("\n")
        src=froms[0]
        adj=[];
        adj.append(froms[1])
    result=line.split()
    froms[0]=(int(result[0]))
    froms[1]=(int(result[1]))
    froms[2]=random.randint(1,10)
    adj.append(froms[1])
file2[froms[0]%partition_size].write(str(src)+"\t")
for j in range(0,len(adj)-1):
        file2[froms[0]%partition_size].write(str(adj[j])+" ")
file2[froms[0]%partition_size].write("\n")

for j in range(0,len(adj)-1):
        file2[i].close()
#       file3.append(open("maiter_sssp.txt"+str(i),"a"))