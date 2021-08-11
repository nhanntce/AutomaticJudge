import java.util.Scanner;
import java.util.Arrays;
public class seperateComment {
    /**
     * sort array inreasing
     * @param args 
     */
    public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextLong();
        }
	//call sort /*function*/ of Arrays libary
        Arrays.sort(arr);
        if (arr.length == 1) {
            System.out.print(arr[arr.length - 1]);
        } else {
            /*comment here*/
            for (int i = 0; i < arr.length - 1; i++) {
                System.out.print(arr[i] + " ");//display
            }
            System.out.print(arr[arr.length - 1]);/*commented between*/
        }
    }
    
    /**
     * display data
     * @param data 
     */
    static void functionChecked(String data){
        //show data
        System.out.println(data);
    }
    
    /**
     * display
     * @param data
     * @return 
     */
    public String display(String data){
        return data;//return value
    }
    //display number
    static int number(){
        System.out.println("this");
        System.out.println("is");
        System.out.println("my");
        System.out.println("code");
        return 261199;
    }
}