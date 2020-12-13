sed -i "s#master#${1}#g" ./*
sed -i "s#9000#${2}#g" ./*
sed -i "s#/home/wangqg/socialite/hadoop-2.6.5#${3}#g" ./*
sed -i "s#/home/wangqg/socialite/jdk1.8.0_161#${4}#g" ./*

