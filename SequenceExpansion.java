import  java.util.*;
class Expansion {
    public static class Capsule {
        Integer multiplier;
        String data;
        public Capsule() { }
        public Capsule(Integer multiplier, String data) {
            this.multiplier = multiplier;
            this.data = data;
        }
        public Integer getMultiplier() {
            return multiplier;
        }
        public void setMultiplier(Integer multiplier) {
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
    static Scanner z=new Scanner (System.in);
    public static void main(String[] args) {
        List<String> orderSequence=new ArrayList<>();
        Map<String,Capsule> map=new HashMap<>();
        for(int i=z.nextInt();i>0;i--){
            orderSequence.add(z.next().toUpperCase());
        }
        for(int i=0;i<orderSequence.size()-1;i++){
            String lhs=z.next().toUpperCase();
            Integer multiplier=z.nextInt();
            String rhs=z.next().toUpperCase();
            map.put(lhs,new Capsule(multiplier,rhs ) );
            if(i==orderSequence.size()-1) map.put(rhs,new Capsule(1,null));
        }
        boolean isLinear=!orderSequence.get(orderSequence.size()-1).equalsIgnoreCase(orderSequence.get(0));
        System.out.println(expand(orderSequence,map,isLinear));
    }
    static String expand(List<String> seq,Map<String ,Capsule> map,boolean linearInput){
        if(linearInput)
        {
            return expansionHelperForLinearInput(seq,new String(),map,1);
        }
        return expansionHelperForNonLinearInput(seq,new String(),map,1);
    }
    static String expansionHelperForLinearInput(List<String> seq, String dat, Map<String ,Capsule> map, int mul){
        for(int i=0;i<seq.size()-1;i++){
            mul=mul*map.get(seq.get(i)).getMultiplier();
            dat+=seq.get(i)+
                    "="+
                    (mul)+"";
        }
        return dat.trim()+seq.get(seq.size()-1);
    }
    static String expansionHelperForNonLinearInput(List<String> seq, String dat, Map<String ,Capsule> map, Integer mul){
       map=regularizeData(seq,map);
        return expansionHelperForLinearInput(seq,dat,map,mul);
    }
    static Map<String,Capsule> regularizeData(List<String> seq, Map<String ,Capsule> map){
        Map<String,Capsule> newMap=new HashMap<>();
        for(int i=0;i<seq.size()-1;i++){
            int upper=map.get(seq.get(i)).getMultiplier(),
            lower=map.get(seq.get(i+1)).getMultiplier();
            int mul=upper/lower;
            newMap.put(seq.get(i), new Capsule(mul,seq.get(i+1)) );
        }
        return newMap;
    }
}
