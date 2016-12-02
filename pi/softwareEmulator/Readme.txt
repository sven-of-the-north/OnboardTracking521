The 521.py a python 3 script, that can be used to emulate the behavior of ODBII system.

How to use it?
-> check the version of python in your Raspbian. If you only have python 2, try to install python 3 or use this guid to convert the script to python 2 :
https://www.raspberrypi.org/documentation/usage/python/more.md 
-> Move to the folder in which you want to create the ODBII files
-> copy the "521.py" and "ODBIICodes.csv" files into the folder. 
		>> python 521.py
-> The script generates a file every 10 seconds until you stop it by killing the process or pressing Ctrl+C.
-> Make sure you kill the process when you are done testing otherwise you may generate too much file and consume too much space from the SD card of your Rpi.
 