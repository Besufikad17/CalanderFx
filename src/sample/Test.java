package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import abushakir.calander.Bahirehasab;
import abushakir.util.Calander_Exceptions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Test {
    public static HashMap<String,Integer> getholidays(){
        HashMap<String,Integer> holidays = new HashMap<>();
        int year = 2012;
        Bahirehasab bh = new Bahirehasab(2012);
        try {
            int seklet = Integer.parseInt(bh.getSingleBealOrTsom("ስቅለት").get("date"));
            holidays.put(bh.getSingleBealOrTsom("ስቅለት").get("month") + "-ስቅለት",seklet);
            int tensae = Integer.parseInt(bh.getSingleBealOrTsom("ትንሳኤ").get("date"));
            holidays.put(bh.getSingleBealOrTsom("ስቅለት").get("month") +"-ትንሳኤ",tensae);
        } catch (Calander_Exceptions.BealNameException e) {
            e.printStackTrace();
        }
        String[] keys = {"መስከረም-አዲስ አመት","መስከረም-መስቀል","ጥቅምት-መውሊድ","ታኅሳስ-ገና","ጥር-ጥምቀት","የካቲት-አደዋ","ሚያዝያ-የሰራተኞች ቀን","ሚያዝያ-የአርበኞች ቀን","ግንቦት-ግንቦት 20","ኃምሌ-ኢደ አል አደሃ"};
        int[] values = {1,17,29,29,11,23,23,27,20,23};
        for (int i = 0;i<keys.length;i++){
            holidays.put(keys[i],values[i]);
        }
        return holidays;
    }

    public static void main(String[] args) {
        String[] arrys = {"A", "B", "C"};
        int[] intarray = {1, 2, 3};
        //System.out.println(Arrays.deepToString(getHashMap(arrys, intarray)));

        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("A", 1);
        multimap.put("A", 0);
        multimap.put("B", 7);
        multimap.put("C", 2);
        multimap.put("C", 6);
        multimap.put("B", 5);
        multimap.put("A", 3);

        System.out.println(multimap.get("B"));
    }


    public static Object[] getHashMap(String[] strarrr, int[] intarr){
        Object[] result = {strarrr, intarr};
        return result;
    }

}
