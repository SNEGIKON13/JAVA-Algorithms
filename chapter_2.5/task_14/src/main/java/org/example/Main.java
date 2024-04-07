package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        List<Domain> domainList = new ArrayList<>();
        List<Domain> reversedList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имена доменов:");
        String domenStr = input.nextLine();
        String[] domensStr = domenStr.split("\\s+");
        for (String domen : domensStr) {
            domainList.add(new Domain(domen));
        }
        for (Domain domain : domainList) {
            domenStr = Domain.reverseDomen(domain.getDomen());
            reversedList.add(new Domain(domenStr));
        }
        Collections.sort(reversedList);
        for (Domain domain : reversedList) {
            System.out.println(domain.getDomen());
        }
    }
}