# Murat Celik b21827263 Quiz3-Problem2
import sys
a = sys.argv[1] # input -- python quiz3-2.py "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22"---
art = a.split(",")
asil=[]
for p in art: # to seperate number
    r = int(p)
    asil.append(r)
acc=0
if 1 in asil:
    asil.remove(1) # to be added later
    acc=1
i = 0
list=[]
list2=[]
while i<6:
    z = asil[i]
    list2.append(z)
    for x in asil[z-2::z]:
        list.append(x)
    for y in asil:
        for z in list:
            if y ==z:
                asil.remove(y) #remove every xth number
    if asil[i] in list2:
        i+=1
if acc==1:
    asil.insert(0,1)
for j in asil: #lucky numbers
    print(j,end=" ")
