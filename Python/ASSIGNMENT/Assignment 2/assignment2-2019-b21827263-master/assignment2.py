# Murat Celik Assignment2 21827263
import sys
a = int(input("What Size Game GoPy?")) # Choose Size
while a<3: # Game Rule a>3
    print("Please, You should choose board size number which greather than two")
    a = int(input("What Size Game GoPy?"))

list = [] # for table empty list
old_variable1 = []
old_variable2 = []
for x in range(0,a*a): # for table add variable
    list.append(str(x))

def result(): # final version of the table after the last step
    k = 1
    for y in list: #table shape
        if k % a ==1:
            print()
        print(y.rjust(a), end=' ')
        k = k + 1

def player1():
    variable1 = int(input('\nPlayer 1 turn --> '))
    if variable1>=a*a or variable1<0: # Game Rule a>3
        print(" Please enter a valid number")
        pass
    elif variable1 not in old_variable1 and variable1 not in old_variable2:
        old_variable1.append(variable1)
        del list[variable1]
        list.insert(variable1,'x')
    elif variable1 in old_variable1 or variable1 in old_variable2:
        if variable1 in old_variable1:
            print("You have made this choice before")
        elif variable1 in old_variable2:
            print("The other player select this cell before.")

def player2():
    variable2 = int(input('\nPlayer 2 turn --> '))
    if variable2>=a*a or variable2<0: # Game Rule a>3
        print(" Please enter a valid number")
        pass
    elif variable2 not in old_variable1 and variable2 not in old_variable2:
        old_variable2.append(variable2)
        del list[variable2]
        list.insert(variable2,'o')
    elif variable2 in old_variable1 or variable2 in old_variable2:
        if variable2 in old_variable1:
            print("The other player select this cell before.")
        elif variable2 in old_variable2:
            print("You have made this choice before")

# oyuncular oynar
def kural1():
    player1()
def kural2():
    player2()

#oyuncular kac kere oynayacak
def kural():
    kural1()
    result()
    if a % 2 == 0: # for even numbers --> step
        step=1
        while step<a**2//2:
            kural2()
            result()
            verticon_o()
            horizon_o()
            diagon_o1()
            diagon_o2()
            kural1()
            result()
            verticon_x()
            horizon_x()
            diagon_x1()
            diagon_x2()
            step +=1
        kural2()
        verticon_o()
        horizon_o()
        diagon_o1()
        diagon_o2()
        result()
        print("\nNo Winner!")
    elif a % 2 == 1: # for odd numbers --> step1
        step1 = 0
        while step1<a**2//2:
            kural2()
            result()
            verticon_o()
            horizon_o()
            diagon_o1()
            diagon_o2()
            kural1()
            result()
            verticon_x()
            diagon_x1()
            horizon_x()
            diagon_x2()
            step1 +=1
        print("\nNo Winner!")

# Check for variable

# check vertically
def verticon_x():
    xlistx = []
    for verticallyx in range(a):
        for verticallyx_2 in range(verticallyx,a**2,a):
            if list[verticallyx_2]=="x":
                xlistx.append(1)
        if xlistx.count(1)==a:
            print("\nWinner : X")
            sys.exit()

            xlistx.clear()
        else:
            xlistx.clear()
def verticon_o():
    olisto = []
    for verticallyo in range(a):
        for verticallyo_2 in range(verticallyo,a**2,a):
            if list[verticallyo_2]=="o":
                olisto.append(1)
        if olisto.count(1)==a:
            print("\nWinner : O")
            sys.exit()
            olisto.clear()
        else:
            olisto.clear()
#check horizontally
def horizon_x():
    xlisty = []
    for horizontallyx in range(0,a**2,a):
        for horizontallyx_2 in range(horizontallyx,horizontallyx+a,1):
            if list[horizontallyx_2]=="x":
                xlisty.append(1)
        if xlisty.count(1)==a:
            print("\nWinner : X")
            sys.exit()
            xlisty.clear()
        else:
            xlisty.clear()
def horizon_o():
    olisty = []
    for horizontallyo in range(0,a**2,a):
        for horizontallyo_2 in range(horizontallyo,horizontallyo+a,1):
            if list[horizontallyo_2]=="o":
                olisty.append(1)
        if olisty.count(1)==a:
            print("\nWinner : O")
            sys.exit()
            olisty.clear()
        else:
            olisty.clear()
# check diagonally
def diagon_x1():
    diaglist=[]
    for diag in range(0,a*a,a+1):
        if list[diag]=="x":
            diaglist.append(1)
    if diaglist.count(1)==a:
        print("\nWinner : X")
        sys.exit()
        diaglist.clear()
    else:
        diaglist.clear()
def diagon_o1():
    diaglisto=[]
    for diago in range(0,a*a,a+1):
        if list[diago]=="o":
            diaglisto.append(1)
    if diaglisto.count(1)==a:
        print("\nWinner : O")
        sys.exit()
        diaglisto.clear()
    else:
        diaglisto.clear()
def diagon_x2():
    diaglistx=[]
    for diagx2 in range(a-1,a*a-a+1,a-1):
        if list[diagx2]=="x":
            diaglistx.append(1)
    if diaglistx.count(1)==a:
        print("\nWinner : X")
        sys.exit()
        diaglistx.clear()
    else:
        diaglistx.clear()
def diagon_o2():
    diaglisto=[]
    for diago2 in range(a-1,a*a-a+1,a-1):
        if list[diago2]=="o":
            diaglisto.append(1)
    if diaglisto.count(1)==a:
        print("\nWinner : O")
        sys.exit()
        diaglisto.clear()
    else:
        diaglisto.clear()

#Let's Begin
result()
kural()
