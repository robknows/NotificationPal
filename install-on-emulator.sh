#!/bin/bash

echo "Starting emulator\n" &&
cd $ANDROID_HOME/Sdk/emulator &&
LD_PRELOAD='/usr/lib/x86_64-linux-gnu/libstdc++.so.6' $ANDROID_HOME/Sdk/emulator/emulator -netdelay none -netspeed full -avd ${1:-Nexus_5_Edited_API_25} &
sleep 25 && # Wait for emulator to finish starting up
echo "Installing app\n" &&
./gradlew installDebug

