#Murat Celik b21827263
import sys
x = int(sys.argv[1])
y = x
z=x
star=list()
star2 = list()
def göster(liste):
    t = -1
    while t < z * 2:
        t += 2
        if t == z * 2 - 1:
            break
        print(liste[t - 1], end="")
        print(liste[t])

for i in range(1,z+1):
    star.append(" "*(x-1))
    star.append("*"*(2*i-1))
    x-=1
for i in range(1,z):
    star2.append(" "*(i))
    star2.append("*" * (2*(y-1) - 1))
    y-=1

göster(star)

print("*"*(2*z-1))

göster(star2)