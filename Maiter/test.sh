#!/bin/bash
a="one two three	four"
#要将$a分割开，可以这样：
OLD_IFS="$IFS"
IFS=" "
arr=($a)
IFS="$OLD_IFS"
echo ${arr[0]}" "${arr[2]}
