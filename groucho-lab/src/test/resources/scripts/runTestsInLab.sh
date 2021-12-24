#!/bin/bash
VERSION="" 
TEST=""

start=$(date)
echo "Running script in: " $PWD
echo "Start time : " $start
echo "Start time : " $start &>>CassiaInLab-$VERSION.txt

for i in {1..10000}
do 
echo " " &>>CassiaInLab-$VERSION.txt
echo "Run N.$i" &>>CassiaInLab-$VERSION.txt
echo "Run N.$i" 
mvn -Dtest=$TEST  -Dmaven.test.failure.ignore=true test -DreuseForks=false -DuseUnlimitedThreads=false &>>CassiaInLab-$VERSION.txt
done

end=$(date)
echo "End time : " $end &>>CassiaInLab-$VERSION.txt
echo "End time : " $end

exit 0
