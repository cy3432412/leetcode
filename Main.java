import java.util.*;
public class Main {
    public static void main(String[] args) {
        nowcode nowcode = new nowcode();
        Solution sol = new Solution();
        Easy easy = new Easy();
        Hard hard = new Hard();
        CharString charString = new CharString();
        String []strs = {"flower","flow","flight"};
        String str = "6e6.5";
        int [][]nums = new int [][]{{0,1},{0,3},{1,2},{1,3},{2,3},{2,4}};
        int []num = new int []{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309};
                //[[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
        //Arrays.sort(arr,(a,b)->a[0]-b[0]);
        // Arrays.sort(intervals,new Comparator<int[]>(){
        //     @Override
        //     public int compare(int[] a,int[] b){
        //         return a[0]-b[0];
        //     }
        // });

        //int [][]matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        //int [][]matrix = new int[][]{{1}};
        System.out.println();
        easy.minOperations(num,134365);
//        [8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309]
//        134365
    }

}
