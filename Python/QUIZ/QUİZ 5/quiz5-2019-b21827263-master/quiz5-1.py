#Murat Celik b21827263
import sys
x = int(sys.argv[1])
y = x
yildiz=1
sayac1 = 1
sayac2 = 1
def scratch(num,star):

    print(" "*(num-1),end="")
    print("*" * star)
    global sayac1
    sayac1 += 1
    if sayac1 == y:
        return orta(x)
    scratch(num-1,star+2)
def orta(sayi):
    print("*" * (2 * sayi - 1))
    return scratch2(x-1,yildiz)
def scratch2(sayi2,star2):
    print(" " * (star2), end="")
    print("*" * (2*sayi2-1))
    global sayac2
    sayac2 += 1
    if sayac2 == y:
        return
    scratch2(sayi2-1,star2+1)
if x==1:
    print("*")
else:
    scratch(x,yildiz)
