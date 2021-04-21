public class Gnome_Sort_Algorithm {
    public static void gnomeSort(Instance_Object[] array){
        int pos = 0;
        while (pos < array.length){
            if(pos==0 || array[pos].number >= array[pos-1].number){//decide whether swap or not
                pos += 1;
            }
            else{
                Instance_Object tmp = array[pos];
                array[pos] = array[pos-1];
                array[pos-1]  = tmp;
                pos -=1;
            }
        }
    }
}
