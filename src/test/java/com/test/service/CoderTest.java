package com.test.service;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by Optical Illusion on 21.06.2015.
 */
public class CoderTest {

    @Test
    public void coder_should_return_valid_answer() throws Exception {

        String inputString = "абвгдеёжзиклмнопрстуфхцчшщыъьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХКЦЧШЩЪЫЬЭЮЯ!№;%:?*()_+ІіЇїёЁҐ^  S f R` §";
        String result = Coder.unCode(Coder.code(inputString));
        assertEquals(inputString, result);

    }
}
