import java.util.Arrays;
import java.util.Random;
/*
*****************************************************
************* Assignment-1 24/03/2021****************
*************       Murat Çelik      ****************
*****************************************************
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Murat Celik b21827263 Assignment 1 - Complexity Analysis");
        System.out.println("time unit = ns = nanosecond\n");
        System.out.print(rightPadding("Input_size",' ',15));
        System.out.print(rightPadding("Comb",' ',15));
        System.out.print(rightPadding("Gnome",' ',15));
        System.out.print(rightPadding("Shaker",' ',15));
        System.out.print(rightPadding("Stooge",' ',15));
        System.out.print(rightPadding("Bitonic",' ',15));
        System.out.println();
        for (int timer = 1; timer <= 12; timer++) {
            Random rd = new Random(); // creating Random object
            String alphabet = "123xyz";
            Instance_Object[] sortedArray = new Instance_Object[(int) Math.pow(2, timer)];
            for (int i = 0; i < sortedArray.length; i++) {
                sortedArray[i] = (new Instance_Object(rd.nextInt(600), alphabet.charAt(rd.nextInt(alphabet.length())))); // storing random integers in an array
            }

            System.out.print(rightPadding(String.valueOf(sortedArray.length),' ',15));


            Instance_Object[] sort_Comb = Arrays.copyOf(sortedArray, sortedArray.length);
            long startTime_Comb = System.nanoTime();
            Comb_Sort_Algorithm.combsort(sort_Comb);
            long endTime_Comb = System.nanoTime();
            long duration_Comb = (endTime_Comb - startTime_Comb);
            System.out.print(rightPadding(String.valueOf(duration_Comb),' ',15));


            Instance_Object[] sort_Gnome = Arrays.copyOf(sortedArray, sortedArray.length);
            long startTime_Gnome = System.nanoTime();
            Gnome_Sort_Algorithm.gnomeSort(sort_Gnome);
            long endTime_Gnome = System.nanoTime();
            long duration_Gnome = (endTime_Gnome - startTime_Gnome);
            System.out.print(rightPadding(String.valueOf(duration_Gnome),' ',15));

            Instance_Object[] sort_Shaker = Arrays.copyOf(sortedArray, sortedArray.length);
            long startTime_Shaker = System.nanoTime();
            Shaker_Sort_Algorithm.ShakerSort(sort_Shaker);
            long endTime_Shaker = System.nanoTime();
            long duration_Shaker = (endTime_Shaker - startTime_Shaker);
            System.out.print(rightPadding(String.valueOf(duration_Shaker),' ',15));


            Instance_Object[] sort_Stooge = Arrays.copyOf(sortedArray, sortedArray.length);
            long startTime_Stooge = System.nanoTime();
            Stooge_Sort_Algorithm.stoogesort(sort_Stooge, 0, sort_Stooge.length - 1);
            long endTime_Stooge = System.nanoTime();
            long duration_Stooge = (endTime_Stooge - startTime_Stooge);
            System.out.print(rightPadding(String.valueOf(duration_Stooge),' ',15));

            Instance_Object[] sort_Bitonic = Arrays.copyOf(sortedArray, sortedArray.length);
            long startTime_Bitonic = System.nanoTime();
            Bitonic_Sort_Algorithm.sort(sort_Bitonic, sort_Bitonic.length, 1);
            long endTime_Bitonic = System.nanoTime();
            long duration_Bitonic = (endTime_Bitonic - startTime_Bitonic);
            System.out.print(rightPadding(String.valueOf(duration_Bitonic),' ',15));


            System.out.println();


        }


    }


    public static String
    leftPadding(String input, char ch, int L)
    {

        String result
                = String

                // First left pad the string
                // with space up to length L
                .format("%" + L + "s", input)

                // Then replace all the spaces
                // with the given character ch
                .replace(' ', ch);

        // Return the resultant string
        return result;
    }

    // Function to perform right padding
    public static String
    rightPadding(String input, char ch, int L)
    {

        String result
                = String

                // First right pad the string
                // with space up to length L
                .format("%" + (-L) + "s", input)

                // Then replace all the spaces
                // with the given character ch
                .replace(' ', ch);

        // Return the resultant string
        return result;
    }
}
