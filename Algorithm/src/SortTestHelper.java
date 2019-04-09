import java.util.Random;

public class SortTestHelper {

    public static Integer[] generateRandArray(Integer n, int rangeL, int rangeR) throws IllegalAccessException {
        if (rangeL > rangeR) {
            throw new IllegalAccessException("rangeL不允许大于rangeR");
        }
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(rangeR) + rangeL;
        }
        return arr;
    }

    public static Integer[] generateNearOrderedArray(Integer n, int swapTimes){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < swapTimes; i++) {
            int posx = random.nextInt(swapTimes)%n;
            int posy = random.nextInt(swapTimes)%n;

            int tmp = arr[posx];
            arr[posx] = arr[posy];
            arr[posy] = tmp;
        }
        return arr;
    }

    public static boolean isSorted(Object[] arr){
        for (int i = 0; i <arr.length; i++)
            if(i != 0 && ((Comparable<Object>)arr[i]).compareTo(arr[i-1]) <0)
                return false;
        return true;
    }

}