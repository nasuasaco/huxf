import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        int n = 50000;
//        Integer [] arr = SortTestHelper.generateRandArray(n,0,n);
        Integer [] arr = SortTestHelper.generateNearOrderedArray(n,100);
        Integer [] arr2 = arr.clone();
        Integer [] arr3 = arr.clone();
        Integer [] arr4 = arr.clone();
        Integer [] arr5 = arr.clone();

        double start = System.currentTimeMillis();
        selectionSort(arr);
        testSort("Selection",arr, start);

        start = System.currentTimeMillis();
        insertionSort(arr2);
        testSort("Insertion",arr2, start);

        start = System.currentTimeMillis();
        insertionSort2(arr3);
        testSort("Insertion2",arr3, start);

        start = System.currentTimeMillis();
        shellSort(arr4);
        testSort("Shell",arr4, start);

        start = System.currentTimeMillis();
        mergeSort(arr5);
        testSort("Merge",arr5, start);

        System.out.println(
                        SortTestHelper.isSorted(arr) +
                " | " + SortTestHelper.isSorted(arr2) +
                " | " + SortTestHelper.isSorted(arr3) +
                " | " + SortTestHelper.isSorted(arr4) +
                " | " + SortTestHelper.isSorted(arr5)
        );

    }

    // 归并排序
    private static void mergeSort(Integer[] arr){
        __mergeSort(arr,0,arr.length-1);
    }

    private static void __mergeSort(Integer[] arr, int l, int r){
        if(l >= r)
            return;

        int mid = (l + r) / 2;
        __mergeSort(arr,l,mid);
        __mergeSort(arr,mid + 1 ,r);
        if(arr[mid] <= arr[mid+1]){
            __merge(arr,l,mid,r);
        }
    }

    private static void __merge(Integer[] arr, int l, int mid, int r){
        Integer [] aux = new Integer[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }
        int i = l,j = mid + 1;

        for (int k = l; k <= r ; k++) {
            if(i > mid){
                arr[k] = aux[j-l];
                j++;
            }else if(j > r){
                arr[k] = aux[i-l];
                i++;
            }else if(aux[i-l] < aux[j-l]){
                arr[k] = aux[i-l];
                i++;
            } else {
                arr[k] = aux[j-l];
                j++;
            }
        }
    }

    // 希尔排序
    private static void shellSort(Integer[] arr){
        int n = arr.length/2;
        int x,j,k = 1;
        while (n >= 1){
            for (int i = 0; i < arr.length; i++) {
                x = arr[i];
                j = i -n;
                //直接插入排序，会向前找所适合的位置
                while(j>=0 && arr[j]>x) {
                    //交换位置
                    arr[j+n]=arr[j];
                    j=j-n;
                }
                arr[j+n] = x;
            }
            n=n/2;
        }
    }

    // 插入排序2
    private static void insertionSort2(Object[] arr){
        for (int i = 1; i <arr.length; i++) {
            //寻找元素arr[i]合适的插入位置
            Object e = arr[i];
            int j; //保存元素e应该插入的位置
            for (j = i; j > 0 &&((Comparable<Object>)arr[j-1]).compareTo(e) >0; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = e;
        }
    }

    // 插入排序
    private static void insertionSort(Object[] arr){
        for (int i = 1; i <arr.length; i++) {
            //寻找元素arr[i]合适的插入位置
            for (int j = i; j > 0 &&
                    ((Comparable<Object>)arr[j-1]).compareTo(arr[j]) >0; j--) {
                Object tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }
        }
    }

    // 选择排序
    private static void selectionSort(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length ; j++) {
                // 寻找最小元素
                if(((Comparable<Object>)arr[i]).compareTo(arr[j]) >0) {
                    Object tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }

    private static void testSort(String sortName, Integer[] arr, double start) {
        String time = (System.currentTimeMillis() - start) + "ms";
        System.out.println(sortName + ": " + time);
    }
}
