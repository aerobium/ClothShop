package com.test.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RAYANT on 22.05.2015.
 */
public class Coder {

    private static HashMap<Character, Character> rus_to_eng = new HashMap<Character, Character>();
    private static HashMap<Character, Character> eng_to_rus = new HashMap<Character, Character>();

    static {

        for (int i = 0; i < 26; i++) {
            rus_to_eng.put((char) ('a' + i), (char) ('а' + i));
        }

        for (int i = 26; i < 32; i++) {
            rus_to_eng.put((char) ('0' + i - 26), (char) ('а' + i));
        }

        rus_to_eng.put('7', 'ё');
        rus_to_eng.put('8', 'Ё');
        rus_to_eng.put('9', 'і');
        rus_to_eng.put('!', 'І');
        rus_to_eng.put('@', 'ї');
        rus_to_eng.put('#', 'Ї');
        rus_to_eng.put('$', 'є');
        rus_to_eng.put('%', 'Є');
        rus_to_eng.put(':', '№');
        for (int i = 41; i < 67; i++) {
            rus_to_eng.put((char) ('A' + i - 41), (char) ('А' + i - 41));
        }
        rus_to_eng.put('^', (char) ('А' + 27));
        rus_to_eng.put('&', (char) ('А' + 28));
        rus_to_eng.put('*', (char) ('А' + 29));
        rus_to_eng.put('(', (char) ('А' + 30));
        rus_to_eng.put(')', (char) ('А' + 31));
        rus_to_eng.put('+', (char) ('А' + 32));

        for (Map.Entry<Character, Character> integerCharacterEntry : rus_to_eng.entrySet()) {
            eng_to_rus.put(integerCharacterEntry.getValue(), integerCharacterEntry.getKey());
        }
    }

    public static String code(String inPut) {
        if (inPut == null) return "";
        String outPut = "";

        for (int i = 0; i < inPut.length(); i++) {
            if (eng_to_rus.containsKey(inPut.charAt(i))) {
                outPut += "‰";
                outPut += eng_to_rus.get(inPut.charAt(i));
            } else outPut += inPut.charAt(i);
        }
        return outPut;
    }

    public static String unCode(String inPut) {
        if (inPut == null) return "";
        String outPut = "";

        for (int i = 0; i < inPut.length(); i++) {

            if (inPut.charAt(i) == '‰') {
                outPut += rus_to_eng.get(inPut.charAt(++i));
            } else outPut += inPut.charAt(i);
        }
        return outPut;
    }
}

