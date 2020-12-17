cd Maiter
make
cd ..
cur_dir=$(cd "$(dirname "$0")"; pwd)
for line in  `cat ~/machines`
do
   scp -r ${cur_dir}/Maiter ${USER}@${line}:${cur_dir}/
done

