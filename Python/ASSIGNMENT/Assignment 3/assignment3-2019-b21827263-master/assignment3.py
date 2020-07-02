#Murat Celik 21827263 Assignment3
import time
import sys
try : #Error Handling
    letter = sys.argv[2] #letter_values
except:
    print("You must write two arguments for this program")
    sys.exit()
letter_f = open(letter , encoding="utf-8-sig")
letter_values = {}
for line in letter_f:
    harf, notu = line.strip().split(':')
    letter_values[harf.strip()] = notu.strip()
letter_f.close()
letter_point ={}
for letter,letter_keys in letter_values.items():
    letter_keys = int(letter_keys)
    letter = letter.replace("I", "ı")
    letter = letter.replace("İ", "i")
    letter = letter.replace (letter,letter.lower())
    letter_point[letter]=letter_keys

shuffled_word_open = sys.argv[1]#correct_words
shuffled_word = open(shuffled_word_open , encoding="utf-8-sig") # for Turkish letter
answer = {}
for line_shuffled in shuffled_word:
    line_shuffled_letter, line_shuffled_word = line_shuffled.strip().split(':')
    answer[line_shuffled_letter.strip()] = line_shuffled_word.strip()
shuffled_word.close()
word_karma=list()
word_cont=list()
for word,word_in in answer.items():
    word = word.replace("I", "ı")# if I convert low => i
    word = word.replace("İ", "i")
    word_in = word_in.replace("I", "ı")
    word_in = word_in.replace("İ", "i")
    word_in =  word_in.lower()
    word =  word.lower()
    word_karma.append(word)
    word_cont.append(word_in)
harf_in_in =list()
for harf_in in word_cont:
    harf_in_in.append(harf_in.split(","))
def random(art):
    print("Shuffled letters are: {} Please guess words for these letters with minimum three letters".format(word_karma[art]))
    puan = 0
    iade = list()
    time_is_up = time.time() + 30
    while time.time() < time_is_up:
        answer = str(input("Guess Word:  ")) #Guess Word input
        answer = answer.replace("I","ı")
        answer = answer.replace("İ","i")
        answer = answer.lower()
        if len(answer)<3:
            print("Please guess words for these letters with minimum three letters")
        if answer not in harf_in_in[art]:
            print("your guessed word is not a valid word")
        else:
            if answer in iade:
                print("This word is guessed before")
            else:
                if time_is_up-time.time()<=0: # if user push enter after 30 seconds, word isn't add list
                    break
                else:
                    iade.append(answer)
                if answer in harf_in_in[art] and answer in iade:
                    control = list(answer)
                    for control_in in control:
                        if time_is_up - time.time() <= 0: # if user push enter after 30 seconds, point is not calculate
                            break
                        else:
                            cont = letter_point.get(control_in)
                            puan = puan + cont*len(control)
        if time_is_up-time.time() <= 0:
            print("You have 0 time")
        else:
            print("You have {} time".format(int(time_is_up-time.time())))#print time


    print("Score for {} is {} and guessed words are:".format(word_karma[art], puan ),end=" ")
    if not iade:
        print("No Guessed Word")
    else:
        for iade_in in iade:
            if iade[-1]==iade_in:
                print(iade_in)
                break
            print(iade_in,end="-")
        iade.clear()
for art in range(len(word_karma)): #call function
    random(art)
    print("\n")
