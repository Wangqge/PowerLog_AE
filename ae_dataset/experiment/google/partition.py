import random
import os
paths=os.getcwd()
os.makedirs(paths+"/maiter")
file1=open("edge.txt","r")
partition_size=16
file2=[]
for i in range(0,partition_size):
        file2.append(open("./maiter/part"+str(i),"w"))
Aadj=[];
froms=[0,0]

i=0
adj=[]
src=0
for line in file1:
        # file2[src%partition_size].write(str(src)+"\t")
        # file3[src%partition_size].write(str(src)+"\t")
        # for j in range(0,len(adj)-1):
        #         file2[src%partition_size].write(str(adj[j])+" ")
        #         file3[src%partition_size].write(str(adj[j])+","+random.randint(1,10)+" ")
        # file2[src%partition_size].write("\n")
        # file3[src%partition_size].write("\n")
        # src=froms[0]
        # adj=[];
        # adj.append(froms[1])
    result=line.split()
    froms[0]=(int(result[0]))
    froms[1]=(int(result[1]))
    if froms[0]%5000000==0:
        print ("current"+str(froms[0]))
    while len(Aadj)<=froms[0]:
        Aadj.append([])
    Aadj[froms[0]].append(froms[1])

print ("finish 1 stage")
print len(Aadj)
for i in range(0,5):
    print(str(len(Aadj[i])))
for i in range(0,len(Aadj)):
    file2[i%partition_size].write(str(i)+"\t")
    if i%1000000==0:
        print ("current"+str(i))
    for j in range(0,len(Aadj[i])):
        file2[i%partition_size].write(str(Aadj[i][j])+" ")
    file2[i%partition_size].write("\n")


for j in range(0,partition_size):
        file2[j].close()

#       file3.append(open("maiter_sssp.txt"+str(i),"a"))

