public class Paciente{

    public static String clasificarEdad (String b) {
        int a = Integer.parseInt(b);
        if (a >= 21 && a <= 30) {
            return "Joven adulto";}
        else if (a > 30 && a <= 60) {
            return "Adulto";}
        else if (a > 60) {
            return "Tercera edad";}
        return null;
    }
}
