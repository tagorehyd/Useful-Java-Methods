import  java.util.*;
public class Expansion {
    public static class Capsule {
        Long multiplier;
        String data;
        public Capsule() { }
        public Capsule(Long multiplier, String data) {
            this.multiplier = multiplier;
            this.data = data;
        }
        public Long getMultiplier() {
            return multiplier;
        }
        public void setMultiplier(Long multiplier) {
            this.multiplier = multiplier;
        }
        public String getData() {
            return data;
        }
        public void setData(String data) {
            this.data = data;
        }
        public String toString() {
            return "Capsule{" +"multiplier=" + multiplier +
                    ", data='" + data + '\'' +'}';
        }
    }
    static String testCase="5 " +
            "university college department student rupees " +
            "university  320 college " +
            "college  7 department " +
            "department  2400 student " +
            "student  80000 rupees";
    static String testCase2="5 " +
            "university college department student rupees " +
            "university 430080000000 rupees " +
            "college 1344000000 rupees " +
            "department 192000000 rupees " +
            "student 80000 rupees";
    static Scanner z=new Scanner (System.in);
    public static void main(String[] args) {
        List<String> orderSequence=new ArrayList<>();
        Map<String,Capsule> map=new HashMap<>();
        for(int i=z.nextInt();i>0;i--){
            orderSequence.add(z.next() );
        }
        for(int i=0;i<orderSequence.size()-1;i++){
            String lhs=z.next() ;
            Long multiplier=z.nextLong();
            String rhs=z.next() ;
            map.put(lhs,new Capsule(multiplier,rhs ) );
            if(i==orderSequence.size()-2) map.put(rhs,new Capsule(1L,null));
        }

        boolean isLinear= !map.get(orderSequence.get(0)).getData().equalsIgnoreCase(map.get(orderSequence.get(orderSequence.size()-2)).getData());

        System.out.println(expand(orderSequence,map,isLinear));
    }
    static String expand(List<String> seq,Map<String ,Capsule> map,boolean linearInput){
        if(linearInput)
        {
            return expansionHelperForLinearInput(seq,new String(),map,1L);
        }
        return expansionHelperForNonLinearInput(seq,new String(),map,1L);
    }
    static String expansionHelperForLinearInput(List<String> seq, String dat, Map<String ,Capsule> map, Long mul){
        for(int i=0;i<seq.size()-1;i++){
            mul=mul*map.get(seq.get(i)).getMultiplier();
            dat+=seq.get(i)+
                    "="+
                    (mul)+"";
        }
        return dat.trim()+seq.get(seq.size()-1);
    }
    static String expansionHelperForNonLinearInput(List<String> seq, String dat, Map<String ,Capsule> map, Long mul){
        map=regularizeData(seq,map);
        return expansionHelperForLinearInput(seq,dat,map,mul);
    }
    static Map<String,Capsule> regularizeData(List<String> seq, Map<String ,Capsule> map){
        Map<String,Capsule> newMap=new HashMap<>();
        for(int i=0;i<seq.size()-1;i++){
            Long upper=map.get(seq.get(i)).getMultiplier(),
                    lower=map.get(seq.get(i+1)).getMultiplier();
            Long mul=upper/lower;
            newMap.put(seq.get(i), new Capsule(mul,seq.get(i+1)) );
        }
        return newMap;
    }
}
