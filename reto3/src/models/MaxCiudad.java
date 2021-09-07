package models;

import java.util.Arrays;


public class MaxCiudad {

    public static boolean contains(String[] array, String item, int length){
        for (int i = 0; i < length; i++){
            if (array[i].equals(item)){
                return true;
            }
        }
        return false;
    }

    public static String[] distinct(Paciente[] array) {
 
        int length = 0;

        String[] items = new String[array.length];
        
        for(Paciente item : array){
            if (!contains(items, item.getCiudad(), length)){
                items[length++] = item.getCiudad();
            }
        }
    
        return Arrays.copyOf(items, length);
    }

    public static String mode(Paciente[] array) {
        
        String[] items = distinct(array);

        int length = items.length;
        int[] counts = new int[length];

        for (int i = 0; i < length; i++) {
            for (Paciente item : array) {
                if (items[i].equals(item.getCiudad())) {
                    counts[i]++;
                }
            }
        }
        return items[max(counts)];
    }

    public static int max(int[] counts){
        
        int index = 0, max = counts[0], length = counts.length;
        
        for (int i = 1; i < length; i++) {
            if (counts[i] > max) {
                index = i;
                max = counts[i];
            }
        }
        return index;
    }
}

