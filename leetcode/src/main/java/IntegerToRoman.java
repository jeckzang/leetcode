import java.util.Arrays;
import java.util.List;

public class IntegerToRoman {
    List<Integer> iList = Arrays.asList(1000,500,100,50,10,5,1);
    List<String> sList = Arrays.asList("M", "D", "C", "L", "X", "V", "I");
    List<String> lList = Arrays.asList("I","X","C");

    public String intToRoman(int num) {
        int l = 0;
        String str = "";
        for(int i = 0;i<iList.size();i++){
            int l1 = num-iList.get(i);
            if (l1 == 0){
                return sList.get(i);
            }
            if (l1>0){
                l = l1;
                str = sList.get(i);
                if (i!=0){
                    for (int j=i;j<i+2 && j<iList.size();j++){
                        int l2 = num-(iList.get(i-1)-iList.get(j));
                        if (l2>=0 && lList.contains(sList.get(j))){
                            if (l2<l1){
                                l = l2;
                                str = sList.get(j)+sList.get(i-1);
                            }
                        }
                    }
                }
                break;
            }
        }
        if (l!=0){
            return str+intToRoman(l);
        }
        return str;
    }




    public static void main(String[] args){
        System.out.println(new IntegerToRoman().intToRoman(1994));
    }
}


