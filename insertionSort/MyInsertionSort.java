public class MyInsertionSort {

    public static void sortAsc(int[] array){

        // 0 ~ n-1

        for(int i = 1; i < array.length ; i++){
            int key = array[i];

            int j  = i - 1;
            while(j > -1 && array[j] > key){
                array[j + 1 ] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void sortDesc(int[] array){

        // 0 ~ n-1

        for(int i = 1; i < array.length ; i++){
            int key = array[i];

            int j  = i - 1;
            while(j > -1 && array[j] < key){
                array[j + 1 ] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

}
