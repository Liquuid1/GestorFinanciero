package modelo;

import java.util.ArrayList;

public class FormateadorNumeros {

    public FormateadorNumeros() {
    }
    
    
    
    public String darFormatoNumero(int num){
        int contPuntos = 0;
        String res = "";
        String Snum = ""+num;
        
        char[] caracteres = Snum.toCharArray();
        
        for (int i = caracteres.length - 1; i >= 0; i--) {
            contPuntos += 1;
            if (contPuntos == 3 && i != 0){
                res = "." + caracteres[i] + res;
                contPuntos = 0;
            } else {
                res = caracteres[i] + res;     
            }


        }
        res = "$" + res;
        return res;
        
    }
    
    public int darFormatoint(String num){
        // Remover el símbolo '$' y los puntos '.'
        String limpio = num.replace("$", "").replace(".", "");
        // Convertir el String limpio a entero
        return Integer.parseInt(limpio);
    }
    
}
