package test;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序算法:冒泡排序
 * 依次找寻最大的放前面
 *
 * @author joels
 * @create 2017-07-19 11:24
 **/
public class BubbleSorter implements Sorter {

    @Override
    public <T extends Comparable<T>> void sort(T[] list) {
        //是否需要交换,若完成排序,则不做多余的循环
        boolean swapped = true;
        for (int i = 1, len = list.length; i < len && swapped; ++i) {
            swapped = false;
            for (int j = 0; j < len - i; ++j) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
        }

    }

    @Override
    public <T> void sort(T[] list, Comparator<T> comp) {
        //是否需要交换,若完成排序,则不做多余的循环
        boolean swapped = true;
        for (int i = 1, len = list.length; i < len && swapped; ++i) {
            swapped = false;
            for (int j = 0; j < len - i; ++j) {
                if (comp.compare(list[j], list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] intArr = new Integer[]{2,4,6,1,99,100,55};
        BubbleSorter sorter = new BubbleSorter();
        sorter.sort(intArr);
        Arrays.asList(intArr).forEach(System.out :: println);
        sorter.sort(intArr,(i1, i2) -> i2 -i1);
        Arrays.asList(intArr).forEach(System.out :: println);

    }
}
