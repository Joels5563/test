package test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestList {

    public static void main(String[] args){
        List<Integer> i1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<Integer> i2 = new ArrayList<>(Arrays.asList(4,5,6,7,8));
        System.out.println("初始化值");
        printList(i1);
        printList(i2);
        i2.removeAll(i1);
        System.out.println("i2.removeAll(i1)之后");
        printList(i1);
        printList(i2);
        i1.addAll(i2);
        System.out.println("i1.addAll(i2)之后");
        printList(i1);
        printList(i2);
    }

    private static void printList(List<Integer> i) {
        i.forEach(System.out::print);
        System.out.println();
    }
}
