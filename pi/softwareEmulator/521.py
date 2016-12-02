#!/usr/bin/env python3
import time
import datetime
import random
total_codes=145
delay = 10
print("File   ,Number of Codes")
while (1):
    s=datetime.datetime.now().strftime("%Y-%m-%d-%H-%M-%S")
    s= "OBDII" + s +".csv"
    f=int(random.expovariate(1/30)+1)
    out=s+","+str(f)
    print(out)
    f1=open(s,'w+')
    for index in range(1,f):
        with open('OBDIICodes.csv', "r") as code_file:
            g=random.randint(1,total_codes);
            for i, line in enumerate(code_file):
                if i == g:
                    f1.write(line)
    time.sleep(delay)
