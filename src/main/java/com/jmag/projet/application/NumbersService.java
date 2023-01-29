package com.jmag.projet.application;

import com.jmag.projet.domain.exceptions.PlanerTreeBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class NumbersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NumbersService.class);
    private static final Integer MAX_ROMAN_NUMBER = 4999;
    private static final TreeMap<Integer, String> romanNumberMap = new TreeMap<>();

    static {
        romanNumberMap.put(1000, "M");
        romanNumberMap.put(900, "CM");
        romanNumberMap.put(500, "D");
        romanNumberMap.put(400, "CD");
        romanNumberMap.put(100, "C");
        romanNumberMap.put(90, "XC");
        romanNumberMap.put(50, "L");
        romanNumberMap.put(40, "XL");
        romanNumberMap.put(10, "X");
        romanNumberMap.put(9, "IX");
        romanNumberMap.put(5, "V");
        romanNumberMap.put(4, "IV");
        romanNumberMap.put(1, "I");
    }


    public String getRomanFromArabicNumber(String strNumber) {

        try {
            Integer.parseInt(strNumber);
        } catch (Exception e) {
            LOGGER.error("Convertion error", e);
            throw new PlanerTreeBadRequestException(String.format("%s is not an integer number!", strNumber));
        }
        if (Integer.parseInt(strNumber) >= MAX_ROMAN_NUMBER) {
            throw new PlanerTreeBadRequestException(
                    String.format("%s is superior than " + MAX_ROMAN_NUMBER + ", " +"conversion impossible !", strNumber));
        }
        int number = Integer.parseInt(strNumber);
        return buildNumber(number);
    }

    private String buildNumber(int number) {

        int inferiorProbableKeyExistingInMap = romanNumberMap.floorKey(number);
        if (number == inferiorProbableKeyExistingInMap) {
            return romanNumberMap.get(number);
        }
        return romanNumberMap.get(inferiorProbableKeyExistingInMap) +
                buildNumber(number - inferiorProbableKeyExistingInMap);
    }
}
