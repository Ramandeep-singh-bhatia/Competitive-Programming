import java.io.*;
import java.util.*;

public class isPrime {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        boolean isPrime = true;
	
	System.out.println("n- "+n);

        for(int i =0; i < n; i++){
            int x = s.nextInt();
		isPrime = true;
		System.out.println("x- "+x);
		System.out.println("isPrime- "+ isPrime);
             if(x%2==0){
		System.out.println("1");
                isPrime = false;
		}
            for(int j = 3; j<=Math.sqrt(x); j+=2){
		System.out.println("j- "+j);
                if(x%j == 0){
		    System.out.println("2");
                    isPrime = false;
			break;
                }else{
		    System.out.println("3");
                    isPrime = true;
		}
            }
		System.out.println("isPrime- "+ isPrime);
            if(isPrime)
                System.out.println("Prime");
            else
                System.out.println("Not Prime");
        }
        
    }
}