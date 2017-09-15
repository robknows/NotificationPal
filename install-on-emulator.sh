#!/bin/bash

if [ "$1" == start-emulator ]
then
echo "Starting emulator" &&
cd $ANDROID_HOME/Sdk/emulator &&
LD_PRELOAD='/usr/lib/x86_64-linux-gnu/libstdc++.so.6' $ANDROID_HOME/Sdk/emulator/emulator -netdelay none -netspeed full -avd "${2:-Pixel_API_25}" &
sleep 90 && # Wait for emulator to finish starting up
echo "Installing app" &&
./gradlew installDebug
elif [ "$1" == already-connected ]
then
echo "Installing app" &&
./gradlew installDebug
else
echo "Usage: \"./install-on-emulator.sh [already-connected | start-emulator]\""
fi

