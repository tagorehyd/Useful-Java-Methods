public class RegularTools{
static ArrayList<ArrayList<String>> combinations(String[]s,int siz){
        ArrayList<ArrayList<String>> arr=new ArrayList<>();
        int c=(int)Math.pow(2,s.length);
        for (int i=0;i<c;i++){
            String pattern=Integer.toBinaryString(c|i).substring(1);
            ArrayList<String>ls=new ArrayList<>();
            int f=0,b=pattern.length();
            while(pattern.contains("1")){
                f=pattern.indexOf("1");
                ls.add(s[f]);
                pattern=pattern.replaceFirst("1","0");
            }
            if(!ls.isEmpty()&&(ls.size()==siz||siz==0))
            arr.add(ls);
        }
        arr.sort(Comparator.comparing(AbstractCollection::toString));
        arr.sort(Comparator.comparing(ArrayList::size));
        return  arr;
        //Regex to match passwords
      //String str="((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,})";
    }
}
