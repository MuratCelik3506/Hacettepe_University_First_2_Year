# Murat Çelik 21827263 Quiz4
import sys
inputfile = sys.argv[1] # inputFile.txt
outputfile = sys.argv[2]#outputFile.txt
output_file=open(outputfile,"w")
input_file=open(inputfile,"r")
line = list()
listnum=set()#just message ID
klist=list()
for line_input in input_file:
    line_input = line_input.split("\n")
    klist.append(line_input[0])
input_file.close()
for line_input2 in klist:
    i,j,k=line_input2.split("\t",2)
    i = int(i)#message ID
    j = int(j)#packet ID
    line2= i,j,line_input2
    line.append(line2)
    listnum.add(i)
listnum=sorted(listnum) #sorted my message ID set
def strateji(list):
    for i in list:
        if i[2]==list[-1][2]:
            output_file.write(i[2])
            output_file.write("\n")
            return i[2]
        output_file.write(i[2])
        output_file.write("\n")
def sortlist1(elem):
    return elem[0]
def sortlist2(elem):
    return elem[1]
sortedlist = sorted(line,key = sortlist1)
Messagewrite=1# Message1/2/3/...
result1=list()
for x in listnum:
    message = ("Message {} \n".format(Messagewrite))#Message write
    output_file.write(message)
    for y in sortedlist:
        if y[0]==x and y not in result1:
            result1.append(y)
    full_result=sorted(result1,key=sortlist2)
    strateji(full_result)
    result1.clear()
    full_result.clear()
    Messagewrite+=1
output_file.close()#close the output.txt for write
outprint=open(outputfile,"r")#open the output.txt for read
print(outprint.read())#print output.txt
outprint.close()
