echo "######################################"
echo "          ARABIC-DATASET              "
echo "######################################"
echo "downloading"

echo "extracting"
unzip edge.txt.zip

echo "preparing edge_sssp.txt"
python sssp.py
echo "finish edge_sssp.txt"

echo "preparing node_katz.txt"
python katz.py
echo "finish node_katz.txt"

echo "preparing node_bp.txt"
python bp.py
echo "finish node_bp.txt"

echo "preparing node_adso.txt"
python adsorption.py
echo "finish node_adso.txt"

echo "preparing data for Maiter"
python adsorption.py
echo "finish data for Maiter"


echo "######################################"
