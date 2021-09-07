import java.util.*;
public class reto2 extends Paciente {

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
        
        for (int i = 0; i < ciudad.length; i++){
            int k = 0;
            ciudadesnombre[i] = ciudad[i];
            for (int j = 0; j < ciudadesnombre.length; j++){
                if (ciudad[j].equals(ciudadesnombre[i])){
                    ciudadesnombre[j] = ciudad[i];
                    ciudadescantidad[j] += 1;
                    break;
                }else{
                    k++;
                }
            }
            if(ciudadesnombre.length==k){
                ciudadesnombre[k+1] = ciudad[i];
                ciudadescantidad[k+1] += 1;
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

        for (int i = 0; i < cantidad; i++) {
            System.out.printf("%s %s%n",paciente[i][1] , Paciente.clasificarEdad(paciente[i][2]));
        }

        leer.close();
    }
}
