package execution;
import core.Uselessfish;

public class Main {
    public static void main(String[] args) {
        String c = Uselessfish.encrypt("diegoxdxd", "holakevin1");
        System.out.println("Texto a encriptar: diegoxdxd");
        System.out.println("Texto encriptado: " + c);
        System.out.println("----- DESENCRIPTADO -----");
        System.out.println(Uselessfish.decrypt(c, "holakevin1"));
        System.out.println("----- DESENCRIPTADO CON CLAVE INCORRECTA -----");
        System.out.println(Uselessfish.decrypt(c, "olahkebin1"));
    }
}
