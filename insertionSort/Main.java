public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{2, 6, 1, 3, 5, 10 , 7};
        MyInsertionSort.sortAsc(array);

        for(int i : array){
            System.out.print(i + " ");
        }

        System.out.println();

        MyInsertionSort.sortDesc(array);
        for(int i : array){
            System.out.print(i + " ");
        }
    }
}
