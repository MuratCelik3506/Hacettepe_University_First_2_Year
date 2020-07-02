# Murat Celik b21827263 Assignment4
import sys
maze1 = sys.argv[1]# first argument
maze2health = sys.argv[2]#second argument
def dosya(dosya_ismi): # open document and processing
    maze_txt = open(dosya_ismi,"r")
    global sayim
    sayim = 0
    global maze
    maze=list()
    global health
    health = int(sys.argv[3])# third argument
    global health_H
    health_H = health
    for maze_txt_line in maze_txt:
        maze.append(list(maze_txt_line))
    i = 0
    while i<len(maze)-1:
        maze[i].remove(maze[i][-1] )
        i+=1
    for i in maze:
        if "H" in i:
            sayim = 1
    global satir_len
    global sıra_len
    satir_len= len(maze)
    sıra_len = len(maze[0])
    global index_satir_S
    global index_sıra_S
    index_satir_S=0
    index_sıra_S=-1
    for i in maze: #to find out where the "S is
        if "S" in i:
            index_sıra_S = i.index("S")
            break
        index_satir_S += 1
    maze_txt.close()
def tablo():
    p = 0
    while True:
        for l in maze:
            global satir_len
            satir_len = len(l)-1
            for k in l:
                if p==len(l)-1:
                    print(k)
                    p=0
                    break

                print(k,end=" ")
                p+=1
        break


def f_kontrol(f_x,f_y): #control around for F
    global health
    if f_y != sıra_len-1:
        if maze[f_x][f_y+1] == "F":
            health-=1
            return True

    if f_x != 0:
        if maze[f_x-1][f_y] == "F":
            health-=1
            return True

    if f_x != satir_len-1:
        if maze[f_x+1][f_y] == "F":
            health-=1
            return True
    if f_y != 0:
        if maze[f_x][f_y-1] == "F":
            health-=1
            return True
    return False

def h_kontrol(h_x,h_y): #control around for H and P
    global health

    if maze[h_x][h_y]=="0":
        maze[h_x][h_y] = maze[h_x][h_y].replace("0","1")
    if h_y != 0:
        if maze[h_x][h_y-1] == "H":
            maze[h_x][h_y-1] = maze[h_x][h_y-1].replace("H","1")
            health=health_H
            return h_kontrol(h_x,h_y-1)

    if h_x != 0:
        if maze[h_x-1][h_y] == "H":
            maze[h_x-1][h_y] = maze[h_x-1][h_y].replace("H","1")
            health=health_H
            return h_kontrol(h_x-1,h_y)

    if h_x != satir_len-1:
        if maze[h_x+1][h_y] == "H":
            maze[h_x+1][h_y] = maze[h_x+1][h_y].replace("H","1")
            health=health_H
            return h_kontrol(h_x+1,h_y)
    if h_y != sıra_len-1:
        if maze[h_x][h_y+1] == "H":
            maze[h_x][h_y+1] = maze[h_x][h_y+1].replace("H","1")
            health=health_H
            return h_kontrol(h_x,h_y+1)
    if h_y != 0:
        if maze[h_x][h_y-1] == "P":
            health-=1
            maze[h_x][h_y-1] = maze[h_x][h_y-1].replace("P","1")
            return h_kontrol(h_x,h_y-1)
    if h_x != 0:
        if maze[h_x-1][h_y] == "P":
            health-=1
            maze[h_x-1][h_y] = maze[h_x-1][h_y].replace("P","1")
            return h_kontrol(h_x-1,h_y)

    if h_x != satir_len-1:
        if maze[h_x+1][h_y] == "P":
            health-=1
            maze[h_x+1][h_y] = maze[h_x+1][h_y].replace("P","1")
            return h_kontrol(h_x+1,h_y)
    if h_y != sıra_len-1:
        if maze[h_x][h_y+1] == "P":
            health-=1
            maze[h_x][h_y+1] = maze[h_x][h_y+1].replace("P","1")
            return h_kontrol(h_x,h_y+1)

    if f_kontrol(h_x,h_y)==1:
        return True
    if True: # if the way is wrong way go to Wrong_way
        wrong_way(h_x,h_y)
        return

def wrong_way(w_x,w_y): # for right way
    global  health

    maze[w_x][w_y] = maze[w_x][w_y].replace("1","0")
    if w_y != 0:
        if maze[w_x][w_y-1] == "1":
            health+=1
            maze[w_x][w_y-1] = maze[w_x][w_y-1].replace("1","0")
            return h_kontrol(w_x,w_y-1)

    if w_y != sıra_len-1:
        if maze[w_x][w_y+1] == "1":
            health+=1
            maze[w_x][w_y+1] = maze[w_x][w_y+1].replace("1","0")
            return h_kontrol(w_x,w_y+1)

    if w_x != 0:
        if maze[w_x-1][w_y] == "1":
            health+=1
            maze[w_x-1][w_y] = maze[w_x-1][w_y].replace("1","0")
            return h_kontrol(w_x-1,w_y)

    if w_x != satir_len-1:
        if maze[w_x+1][w_y] == "1":
            health+=1
            maze[w_x+1][w_y] = maze[w_x+1][w_y].replace("1","0")
            return h_kontrol(w_x+1,w_y)
def cikti(): # for write output.txt
    global output_txt
    output_txt = sys.argv[4]
    output = open(output_txt,"a")
    print(health)
    for i in maze:
        pk=0
        for k in i:
            k=k.replace("P","0")
            k=k.replace("W","0")
            output.write(k)
            pk+=1
            if pk == satir_len+1:
                break
            output.write(", ")
        output.write("\n")
    if sayim == 1:
        output.write("Health : {}".format(health))
    output.write("\n\n")
    output.write("********************\n\n")
    output.close()


dosya(maze1)#first argument
h_kontrol(index_satir_S,index_sıra_S)
tablo()
cikti()

dosya(maze2health)#second argument
print()
h_kontrol(index_satir_S,index_sıra_S)
tablo()
cikti()


