#!/bin/bash
cd ~/Android/Sdk/emulator && LD_PRELOAD='/usr/lib/x86_64-linux-gnu/libstdc++.so.6' ~/Android/Sdk/emulator/emulator -netdelay none -netspeed full -avd Nexus_5_Edited_API_25 &
cd ~/StudioProjects/NotificationPal && sleep 20 && ./gradlew installDebug
