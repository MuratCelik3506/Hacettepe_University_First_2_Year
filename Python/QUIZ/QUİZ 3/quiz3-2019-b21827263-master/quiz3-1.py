# Murat Celik b21827263 Quiz3-Problem1
import sys
a = int(sys.argv[1]) #input python quiz3-1.py a b
b = int(sys.argv[2])

def digit(exp): #digit
    print("=",end=" ")
    list=[]
    while exp>0:
        c = exp%10
        list.insert(0,c)
        exp=exp//10
    k=0
    while k<len(list):
        if k+1 == len(list) :
            print("{}".format(list[k]),end=(" "))
            break
        print("{}".format(list[k]),end=(" + "))
        k +=1
    d=0
    for i in list:
        d = d + i
    print("= {}".format(d),end=(" "))
    if d>9:
        digit(d)
exp = a**b #exponential
print("{0}^{1} = {2}".format(a,b,exp),end=" ")
digit(exp)
