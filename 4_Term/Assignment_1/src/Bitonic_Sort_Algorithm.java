public class Bitonic_Sort_Algorithm {
    //This place decides whether the order will
                    //be increasing or decreasing and shifts places.
    private static void compAndSwap(Instance_Object a[], int i, int j, int ascending)
    {
        if ( (a[i].number > a[j].number && ascending == 1) ||
                (a[i].number < a[j].number && ascending == 0))
        {
            Instance_Object temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    private static void bitonicMerge(Instance_Object a[], int low, int input_length, int ascending) {
        if (input_length>1) {
            int k = input_length/2;
            for (int i=low; i<low+k; i++)
                compAndSwap(a,i, i+k, ascending);
            bitonicMerge(a,low, k, ascending);
            bitonicMerge(a,low+k, k, ascending);
        }
    }

    private static void bitonicSort(Instance_Object a[], int low, int input_length, int ascending) {
        if (input_length>1)
        {
            int k = input_length/2;

            bitonicSort(a, low, k, 1);// sort in ascending order
            bitonicSort(a,low+k, k, 0);// sort in descending
            bitonicMerge(a, low, input_length, ascending);//merge whole sequence in ascending order
        }
    }
//Call  the entire array of length N for sorting in ascending order
    public static void sort(Instance_Object a[], int N, int up) {
        bitonicSort(a, 0, N, up);
    }

}


