package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Этот класс реализует алгоритм сортировки подсчетом (counting sort) с использованием Queue.
 */
public class CountingSort {

    /**
     * Основной метод демонстрирует использование метода countingSort.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        int[] a = {2456, 4, 145, 3, 2321, 111, 465, 3};
        int R = Arrays.stream(a).max().orElse(0) + 1; // Количество возможных значений в массиве a

        countingSort(a, R);

        System.out.println("Отсортированный массив: " + Arrays.toString(a));
    }

    /**
     * Сортирует массив целых чисел с использованием алгоритма сортировки подсчетом (counting sort)
     * с использованием Queue.
     *
     * @param a Массив, который нужно отсортировать.
     * @param R Количество возможных значений в массиве a.
     */
    public static void countingSort(int[] a, int R) {
        int N = a.length;
        Queue<Integer>[] queues = new Queue[R];

        // Инициализация очередей в массиве
        for (int i = 0; i < R; i++) {
            queues[i] = new LinkedList<>();
        }

        // Распределение элементов в соответствующие очереди
        for (int i = 0; i < N; i++) {
            queues[a[i]].add(a[i]);
        }

        // Сбор элементов из очередей обратно в исходный массив
        int index = 0;
        for (int i = 0; i < R; i++) {
            while (!queues[i].isEmpty()) {
                a[index++] = queues[i].poll();
            }
        }
    }
}