package com.test.service;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by RAYANT on 17.05.2015.
 */
public class Services {

    public static String getRandomPassword() {


        Map<Integer,Character> dig = new HashMap<Integer,Character>();
        Map<Integer,Character> bigCh = new HashMap<Integer,Character>();
        Map<Integer,Character> lowCh = new HashMap<Integer,Character>();
        ByteArrayOutputStream br = new ByteArrayOutputStream();

        int digCount = 0;
        int lowChCount = 0;
        int bigChCount = 0;

        for (int i = 0; i < 10; i++)
        {
            char data =(char)('0'+ i);
            dig.put(i,data);
        }

        for (int i = 0; i < 26; i++){

            char low = (char)('a'+ i);
            char big = (char)('A'+ i);
            bigCh.put(i,big);
            lowCh.put(i,low);
        }

        while (true){

            for (int i = 0; i < 8; i++)
            {
                int random = (int)(Math.random() * 3);


                if (i>5) switch (random)
                {
                    case 0:
                        if (digCount == 0) random = 0;
                        if (lowChCount == 0) random = 1;
                        if (bigChCount == 0) random = 2;
                        break;
                    case 1:
                        if (digCount == 0) random = 0;
                        if (bigChCount == 0) random = 2;
                        if (lowChCount == 0) random = 1;
                        break;
                    case 2:
                        if (bigChCount == 0) random = 2;
                        if (lowChCount == 0) random = 1;
                        if (digCount == 0) random = 0;
                        break;
                }

                switch (random){

                    case 0:{
                        int localRandom = (int)(Math.random() * 10);
                        br.write(dig.get(localRandom));
                        digCount++;
                        break;
                    }
                    case 1:{
                        int localRandom = (int)(Math.random() * 26);
                        br.write(lowCh.get(localRandom));
                        lowChCount++;
                        break;
                    }
                    case 2:{
                        int localRandom = (int)(Math.random() * 26);
                        br.write(bigCh.get(localRandom));
                        bigChCount++;
                        break;
                    }
                }

            }
            break;

        }

        return br.toString();

    }

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static HashSet<String> parser (String input){

        HashSet<String> result = new HashSet<String>();
        if (!input.isEmpty()) {
            String[] res = input.substring(0, input.length() - 1).split(" ");

            for (String s : res) {
                if (result.contains(s)) result.remove(s);
                else result.add(s);
            }

        }
        return result;
    }

}
