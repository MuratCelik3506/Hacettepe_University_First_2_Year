public class Shaker_Sort_Algorithm {

    public static void ShakerSort(Instance_Object[] A){
        boolean swapped;
        do{
            swapped = false;
            for(int i = 0; i < A.length-1;i++){
                if (A[i].number > A[i + 1].number) { // test whether the two elements are in the wrong order
                    Instance_Object tmp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = tmp;
                    swapped =true;
                }
            }
            if (!swapped){
                break;
            }
            swapped = false;
            for(int i = A.length-2; i >= 0 ;i--){
                if (A[i].number > A[i + 1].number) { // test whether the two elements are in the wrong order
                    Instance_Object tmp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = tmp;
                    swapped =true;
                }
            }
        }while(swapped);
    }
}
