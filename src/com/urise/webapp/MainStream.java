package com.urise.webapp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] nums = new int[15];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (Math.random() * 9 + 1);
        }

        int minValue = minValue(nums);
        System.out.println("Min уникальное число: " + minValue);

        List<Integer> integerList = oddOrEven(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        System.out.print("Метод oddOrEven: ");
        integerList.forEach(System.out::print);
    }

    private static int minValue(int[] values) {

        int result = Arrays.stream(values)
                .boxed()
                .distinct()
                .sorted()
                .reduce((x, y) -> x * 10 + y)
                .orElseThrow();

        return result;
    }


    private static List<Integer> oddOrEven(List<Integer> integers) {
        long sum = integers
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        return integers
                .stream()
                .filter(i -> (sum % 2 == 0) == (i % 2 != 0))
                .toList();
    }
}
