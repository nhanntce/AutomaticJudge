import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class A {

    /**
     *
     * @param ar1
     * @param ar2
     * @param ar3
     * @return
     */
    ArrayList<Integer> findCommon(int ar1[], int ar2[], int ar3[]) {
        // Initialize starting indexes for ar1[], ar2[] and ar3[]
        int i = 0, j = 0, k = 0;
        ArrayList<Integer> res = new ArrayList<>();

        // Iterate through three arrays while all arrays have elements
        while (i < ar1.length && j < ar2.length && k < ar3.length) {
            // If x = y and y = z, print any of them and move ahead
            // in all arrays
            if (ar1[i] == ar2[j] && ar2[j] == ar3[k]) {
                res.add(ar1[i]);
                i++;
                j++;
                k++;
            } // x < y
            else if (ar1[i] < ar2[j]) {
                i++;
            } // y < z
            else if (ar2[j] < ar3[k]) {
                j++;
            } // We reach here when x > y and z < y, i.e., z is smallest
            else {
                k++;
            }
        }
        return res;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        // If x = y and y = z, print any of them and move ahead
        // in all arrays
        A ob = new A();
        Scanner sc = new Scanner(System.in);
        int n1, n2, n3;
        int arr1[], arr2[], arr3[];
        n1 = sc.nextInt();
        arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            arr1[i] = sc.nextInt();
        }
        n2 = sc.nextInt();
        arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            arr2[i] = sc.nextInt();
        }
        // If x = y and y = z, print any of them and move ahead
        // in all arrays
        // If x = y and y = z, print any of them and move ahead
        // in all arrays
        n3 = sc.nextInt();
        arr3 = new int[n3];
        for (int i = 0; i < n3; i++) {
            arr3[i] = sc.nextInt();
        }
        ArrayList<Integer> res = ob.findCommon(arr1, arr2, arr3);
        for (int i = 0; i < res.size() - 1; i++) {
            System.out.print(res.get(i) + " ");
        }
        if (!res.isEmpty()) {
            System.out.print(res.get(res.size() - 1));
        }

    }
}
