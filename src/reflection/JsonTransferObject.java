package reflection;

import org.json.simple.JSONObject;
import utils.SetterHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonTransferObject {

    public void transferir(Object object, JSONObject... jsons) throws InvocationTargetException, IllegalAccessException {

        Method[] metodosMolde = object.getClass().getDeclaredMethods();
        for (int i = 0; i < metodosMolde.length; i++) {
            if (metodosMolde[i].getName().substring(0, 3).equals("set")) {
                for (int j = 0; j < jsons.length; j++) {
                    String nombreParametroBuscado = prepararNombreParametro(metodosMolde[i].getName().substring(3));
                    if (jsons[j].get(nombreParametroBuscado) != null) {
                        new SetterHelper().ejecutarSet(jsons[j].get(nombreParametroBuscado), object, metodosMolde[i]);
                    }
                }
            }
        }
    }

    private String prepararNombreParametro(String substring) {
        char primeraLetra = substring.charAt(0);
        return  Character.toLowerCase(primeraLetra)+ substring.substring(1);
    }
}