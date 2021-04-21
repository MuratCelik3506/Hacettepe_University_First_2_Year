import static java.lang.Math.floor;

public class Comb_Sort_Algorithm {
    public static void combsort( Instance_Object[] input ){
        int gap = input.length;
        double shrink = 1.3;// the best choice for shrink
        boolean sorted = false;
        while(!sorted ){
            gap = (int) floor(gap / shrink);//Rearrange gap
            if (gap <= 1){
                gap = 1;
                sorted = true;
            }

            int i = 0;
            while((i+gap)<input.length) {
                if (input[i].number > input[i + gap].number){//decide whether swap or not
                    Instance_Object tmp = input[i];
                    input[i] = input[i + gap];
                    input[i + gap] =tmp;
                    sorted = false;
                }
                i++;
            }
        }
    }

}
