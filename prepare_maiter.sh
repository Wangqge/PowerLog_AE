cd Maiter
make
cd ..
cur_dir=$(cd "$(dirname "$0")"; pwd)
for line in  `cat ~/machines`
do
   scp -r ${cur_dir}/ae_dataset/experiment/arabic/maiter ${USER}@${line}:${cur_dir}/ae_dataset/experiment/arabic
   scp -r ${cur_dir}/ae_dataset/experiment/google/maiter ${USER}@${line}:${cur_dir}/ae_dataset/experiment/google
   scp -r ${cur_dir}/ae_dataset/experiment/clue/maiter ${USER}@${line}:${cur_dir}/ae_dataset/experiment/clue
   scp -r ${cur_dir}/ae_dataset/experiment/wiki/maiter ${USER}@${line}:${cur_dir}/ae_dataset/experiment/wiki
   scp -r ${cur_dir}/Maiter ${USER}@${line}:${cur_dir}/
done

