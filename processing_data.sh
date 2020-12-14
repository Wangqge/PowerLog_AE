DOWNLOAD_HOST=http://hercules.cse.ohio-state.edu
wget $DOWNLOAD_HOST/live/edge.txt.zip
mv ./edge.txt.zip ./ae_dataset/experiment/live/
wget $DOWNLOAD_HOST/flickr/edge.txt.zip
mv ./edge.txt.zip ./ae_dataset/experiment/flickr/
wget $DOWNLOAD_HOST/arabic/edge.txt.zip
mv ./edge.txt.zip ./ae_dataset/experiment/arabic
wget $DOWNLOAD_HOST/wiki/edge.txt.zip
mv ./edge.txt.zip ./ae_dataset/experiment/wiki/
wget $DOWNLOAD_HOST/clue/edge.txt.zip
mv ./edge.txt.zip ./ae_dataset/experiment/clue/
wget $DOWNLOAD_HOST/orkut/edge.txt.zip
mv ./edge.txt.zip ./ae_dataset/experiment/orkut/

cd ./ae_dataset/experiment/google
./prepare_data_sub.sh
cd ../../..
cd ./ae_dataset/experiment/arabic
./prepare_data_sub.sh
cd ../../..
cd ./ae_dataset/experiment/wiki
./prepare_data_sub.sh
cd ../../..
cd ./ae_dataset/experiment/clue
./prepare_data_sub.sh
cd ../../..
cd ./ae_dataset/experiment/flickr
./prepare_data_sub.sh
cd ../../..
cd ./ae_dataset/experiment/live
./prepare_data_sub.sh
cd ../../..
cd ./ae_dataset/experiment/orkut
./prepare_data_sub.sh
cd ../../..

