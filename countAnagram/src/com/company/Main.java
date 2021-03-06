package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.jar.Pack200;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.print(countAnagram("abc", "aeqefbeqer")); //0
        System.out.print(countAnagram("abc", "aedbacewcab")); //2
        System.out.print(countAnagram("abc", "abcabcabc")); //3
    }

    // abc     ,   baefcabio
    public static int countAnagram(String shortStr, String longStr) {
        if(shortStr.length() > longStr.length()) {
            return -1;
        }

        HashMap<Character, Integer> hm = new HashMap<>();
        int s_length = shortStr.length();
        int counter = 0;


        //put shorter string into Hash Map (key -> Char, val -> count of the char)
        for(int i = 0 ; i < shortStr.length(); i++) {
            if(hm.containsKey(shortStr.charAt(i))) {
                hm.put(shortStr.charAt(i), hm.get(shortStr.charAt(i))+1);
            } else {
                hm.put(shortStr.charAt(i), 1);
            }
        }
        HashMap<Character, Integer> hm_c = new HashMap<Character, Integer>(hm);


        //loop thru the longer string
        for(int i = 0; i < longStr.length(); i++) {
            //if the hashmap containsKey the character and the value is bigger than 0, subtract 1 value for that in hashmap
            //and subtract the length counter by 1 as well
            if(hm_c.containsKey(longStr.charAt(i)) && hm_c.get(longStr.charAt(i)) > 0 ) {
                hm_c.put(longStr.charAt(i), hm_c.get(longStr.charAt(i))-1);
                s_length--;
            } else if(hm_c.containsKey(longStr.charAt(i)) && hm_c.get(longStr.charAt(i)) <= 0 ) {
                s_length = shortStr.length() - 1;
                hm_c = new HashMap<>(hm);
                hm_c.put(longStr.charAt(i), hm_c.get(longStr.charAt(i))-1);
            } else if(!hm_c.containsKey(longStr.charAt(i))) {
                s_length = shortStr.length();
                hm_c = new HashMap<>(hm);
            }
            //when the length counter reaches 0, which means we just saw anagram of the short string in the long string
            if(s_length == 0) {
                counter++;
            }
        }

        return counter;

    }



}
