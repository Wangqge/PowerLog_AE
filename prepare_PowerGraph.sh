
cd PowerGraph 
./configure
cd release/toolkits/graph_analytics
make sssp
make connected_component
cd ..
cur_dir=$(cd "$(dirname "$0")"; pwd)
for line in  `cat ~/machines`
do
   scp -r ${cur_dir}/ae_dataset/experiment/flickr ${USER}@${line}:${cur_dir}/ae_dataset/experiment
   scp -r ${cur_dir}/ae_dataset/experiment/arabic ${USER}@${line}:${cur_dir}/ae_dataset/experiment
   scp -r ${cur_dir}/ae_dataset/experiment/clue ${USER}@${line}:${cur_dir}/ae_dataset/experiment
   scp -r ${cur_dir}/ae_dataset/experiment/wiki ${USER}@${line}:${cur_dir}/ae_dataset/experiment
   scp -r ${cur_dir}/PowerGraph ${USER}@${line}:${cur_dir}/
done



