package testformat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NhanNT
 */
public class C
{

    // This function prints common elements in ar1
ArrayList<Integer> findCommon(int ar1[], int ar2[], int ar3[])
{
    // Initialize starting indexes for ar1[], ar2[] and ar3[]
    int i = 0, j = 0, k = 0;
    ArrayList<Integer> res = new ArrayList<>();

    // Iterate through three arrays while all arrays have elements
    while (i < ar1.length && j < ar2.length && k < ar3.length)
    {
            // If x = y and y = z, print any of them and move ahead
            // in all arrays
            if (ar1[i] == ar2[j] && ar2[j] == ar3[k])
            {
                res.add(ar1[i]);
                i++;
                j++;
                k++;
            } // x < y
                                                        else if (ar1[i] < ar2[j])
                                                        {
                                                            i++;
                                                        } // y < z
                                                        else if (ar2[j] < ar3[k])
                                                        {
                                                            j++;
                                                        } // We reach here when x > y and z < y, i.e., z is smallest
                                                        else
            {
                k++;
            }
        }
        return res;
    }

    // Driver code to test above
    public static void main(String args[])
    {
        try
        {
            java.io.PrintStream fileOut;
            try
            {
                fileOut = new java.io.PrintStream("A.out");
                System.setOut(fileOut);
            }
            catch (java.io.FileNotFoundException ex)
            {
            }
            C ob = new C();
            Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(new File("A.inp"))));
            int n1, n2, n3;
            int arr1[], arr2[], arr3[];
            n1 = sc.nextInt();
            arr1 = new int[n1];
            for (int i = 0; i < n1; i++)
            {
                arr1[i] = sc.nextInt();
            }
            n2 = sc.nextInt();
            arr2 = new int[n2];
            for (int i = 0; i < n2; i++)
            {
                            arr2[i] = sc.nextInt();
                        }
                        n3 = sc.nextInt();
                        arr3 = new int[n3];
                        for (int i = 0; i < n3; i++)
                        {
                            arr3[i] = sc.nextInt();
                        }
            ArrayList<Integer> res = ob.findCommon(arr1, arr2, arr3);
            for (int i = 0; i < res.size() - 1; i++)
            {
                System.out.print(res.get(i) + " ");
            }
            if (!res.isEmpty())
            {
                System.out.print(res.get(res.size() - 1));
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
