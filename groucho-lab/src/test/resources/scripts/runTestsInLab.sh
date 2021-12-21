#!/bin/bash
TEST="com.alibaba.json.bvt.serializer.JSONSerializerTest2"
VERSION="1.2.54"
for i in {1..10000}
do 
echo " " &>>inLab-$VERSION-$TEST.txt
echo "Run N.$i" &>>inLab-$VERSION-$TEST.txt
mvn -Dtest=$TEST test &>>inLab-$VERSION-$TEST.txt
done
exit 0
