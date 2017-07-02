#!/bin/bash

if [ "$1" == start-emulator ]
then
echo "Starting emulator" &&
cd $ANDROID_HOME/Sdk/emulator &&
LD_PRELOAD='/usr/lib/x86_64-linux-gnu/libstdc++.so.6' $ANDROID_HOME/Sdk/emulator/emulator -netdelay none -netspeed full -avd "${2:-Pixel_API_25}" &
sleep 35 && # Wait for emulator to finish starting up
echo "Running tests on emulator" &&
./gradlew connectedAndroidTest
elif [ "$1" == already-connected ]
then
echo "Running tests on emulator" &&
./gradlew connectedAndroidTest
else
echo "Usage: \"./run-instrumented-tests.sh [already-conneted | start-emulator]\""
fi

