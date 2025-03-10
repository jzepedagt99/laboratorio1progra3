package com.mylaboratorio1.laboratorio1;

import java.util.Stack;

/**
 *
 * @author jzepeda
 */
public class Laboratorio1 {

    public static void main(String[] args) {
        
        String expresionInfija = "(A*B) + (B/2)/2*8";
        String expresionPosfija = convertToPostfix(expresionInfija);        
        
        String expresionTemp = reverseString(expresionInfija);
        expresionTemp = convertToPostfix(expresionTemp);
        
        String expresionPrefija = reverseString(expresionTemp);
        
        System.out.println("Expresion original = " + expresionInfija + " convertida a postFija = " + expresionPosfija);
        
        System.out.println("Expresion original = " + expresionInfija + " convertida a Prefija = " + expresionPrefija);
        
    }
    
    public static String convertToPostfix(String expresion){
        
        char valor;
        String expresionConvert = "";
        Stack<Character> pila = new Stack<>();
        
        for (int i = 0; i < expresion.length(); i++) {
            valor = expresion.charAt(i);
            if (Character.isDigit(valor) || Character.isLetter(valor)) {
                expresionConvert = expresionConvert+valor;
            }else if(isOperador(valor)){
                
                if (pila.isEmpty()) {
                    pila.push(valor);
                    continue;
                }
                
                int jerarquiaPila = validateHierarchy(valor);
                int jerarquiaActual = validateHierarchy(valor);
                
                if (pila.peek() == '(') {
                    pila.push(valor);
                }else{
                    if (jerarquiaPila>=jerarquiaActual){
                        expresionConvert = expresionConvert + pila.peek();
                        pila.pop();
                        pila.push(valor);
                    }
                }                                
                
            }else if(valor == '(' || valor == ')'){
                if (valor == '(') {
                    pila.push(valor);
                }else{
                    while(!pila.isEmpty() && pila.peek() != '('){
                        expresionConvert = expresionConvert + pila.peek();
                        pila.pop();
                    }                    
                    pila.pop();
                }
            }
        }
        
        while (!pila.isEmpty()) {            
            expresionConvert = expresionConvert + pila.peek();
            pila.pop();
        }
        
        return  expresionConvert;
    }
    
    public static boolean isOperador(char operador){        
        return operador == '+' || operador == '-' || operador == '/' || operador == '*';
    }
    
    public static int validateHierarchy(char valor){
        int resultado = 0;
        
        if (valor == '+' || valor == '-') {
            resultado = 1;
        }else if (valor == '/' || valor == '*'){
            resultado = 2;
        }
        
        return resultado;
    }
    
    public static String reverseString(String expresion){        
        StringBuilder resultado = new StringBuilder();
    
        for (int i = expresion.length() - 1; i >= 0; i--) {
            char c = expresion.charAt(i);
            if (c == '(') {
                resultado.append(')');
            } else if (c == ')') {
                resultado.append('(');
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }
}
