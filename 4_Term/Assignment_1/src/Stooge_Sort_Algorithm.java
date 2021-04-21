public class Stooge_Sort_Algorithm {
    static void stoogesort(Instance_Object arr[], int l, int h) {
        if (l >= h)
            return;

        // If second element is bigger than first, swap them
        if (arr[l].number > arr[h].number) {
            Instance_Object t = arr[l];
            arr[l] = arr[h];
            arr[h] = t;
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3; //Rearrange t value

            stoogesort(arr, l, h - t);// sort first 2/3 elements
            stoogesort(arr, l + t, h);//sort last 2/3 elements
            stoogesort(arr, l, h - t);// sort first 2/3 elements,again
        }
    }
}
