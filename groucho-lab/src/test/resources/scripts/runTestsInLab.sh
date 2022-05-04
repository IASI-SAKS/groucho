#!/bin/bash
VERSION="" 
TEST=""

start=$(date)
echo "Running script in: " $PWD
echo "Start time : " $start
echo "Start time : " $start &>>OriginalTestInLab-$VERSION.txt

for i in {1..10000}
do 
echo " " &>>OriginalTestInLab-$VERSION.txt
echo "Run N.$i" &>>OriginalTestInLab-$VERSION.txt
echo "Run N.$i" 
mvn -Dtest="$TEST"  -Dmaven.test.failure.ignore=true test -DreuseForks=false -DuseUnlimitedThreads=false &>>OriginalTestInLab-$VERSION.txt
done

end=$(date)
echo "End time : " $end &>>OriginalTestInLab-$VERSION.txt
echo "End time : " $end

exit 0
