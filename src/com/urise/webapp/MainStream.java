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
        int result = 0;
        int numIndex = 1;

        int[] ints = Arrays.stream(values)
                .boxed()
                .distinct()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue).toArray();

        for (int i = 0; i < ints.length; i++) {
            result = result + ints[i] * numIndex;
            numIndex = numIndex * 10;
        }
        return result;
    }


    private static List<Integer> oddOrEven(List<Integer> integers) {
        long sum = integers.stream().mapToInt(i -> i).summaryStatistics().getSum();
        if (sum % 2 == 0) {
            return integers.stream().filter(x -> x % 2 == 0).distinct().collect(Collectors.toList());
        } else {
            return integers.stream().filter(x -> x % 2 != 0).distinct().collect(Collectors.toList());
        }
    }
}
