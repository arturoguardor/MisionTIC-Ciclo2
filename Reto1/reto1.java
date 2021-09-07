import java.util.*;
public class reto1 {
    public static void main(String[] args)throws Exception{
        Scanner leer = new Scanner(System.in);
        String num = leer.nextLine();
        int cantidad = Integer.parseInt(num);
        String paciente[][] = new String [cantidad][6];
        String[] ciudad = new String [cantidad];
        String ciudadesnombre[]= new String[cantidad];
        int ciudadescantidad[]= new int[cantidad];
        
        for (int i = 0; i<cantidad ; i++){
            
            String lectura[] = leer.nextLine().split("-");
            
            for (int j = 0; j < lectura.length; j++){
                paciente[i][j] = lectura[j];
                if (j == 3){
                    ciudad[i] = paciente[i][j]; 
                }
            }
        }
        
        Arrays.sort(ciudad);
        String auxiliar = ciudad[0];
        int j = 0;

        for (int i = 0; i < ciudad.length; i++){
            
            if (auxiliar.equals(ciudad[i])){
                ciudadesnombre[j] = ciudad[i];
                ciudadescantidad[j] += 1;

            }else{
                auxiliar = ciudad[i];
                ciudadesnombre[j+1] = ciudad[i];
                ciudadescantidad[j+1] += 1;
                j++;
            }
        }
        
        String mayornombre = ciudadesnombre[0];
        int mayorcantidad = 0;
        for(int i = 0; i < ciudadescantidad.length; i++) {
            if (ciudadescantidad[i] > mayorcantidad){
                mayornombre = ciudadesnombre[i];
                mayorcantidad = ciudadescantidad[i];
            }

        }

        System.out.println(mayornombre);

        leer.close();
    }
}
