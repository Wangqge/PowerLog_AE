cur_dir=$(cd "$(dirname "$0")"; pwd)
for line in  `cat ~/machines`
do
   ssh -n ${USER}@${line} "mv ${cur_dir}/openmpi ${cur_dir}/openmpi_copy"
done
