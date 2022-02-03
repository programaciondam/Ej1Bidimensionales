/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej1bidimensionales;

import fechas.Fecha;
import numeros.Numero;
import textos.Textos;

/**
 *
 * @author dam
 */
public class Concesionario {

    Modelo[] modelos;
    String[] tipos;
    float[] limites;
    float[][] beneficios;

    public void inicializar() {
        tipos = new String[]{"Turismo", "Deportivo", "Todo Terreno"};
        limites = new float[]{15000f, 30000f, 50000f,
            100000f, Float.MAX_VALUE};
        beneficios = new float[][]{
            {0.15f, 0.10f, 0.10f, 0.18f, 0.06f},
            {0.18f, 0.12f, 0.14f, 0.2f, 0.08f},
            {0.21f, 0.14f, 0.16f, 0.22f, 0.1f}
        };

    }

    public Concesionario() {
        inicializar();
        modelos = new Modelo[15];
    }

    public Concesionario(int numeroModelos) {
        inicializar();
        modelos = new Modelo[numeroModelos];
    }

    public void crear() {
        for (int pos = 0; pos < modelos.length; pos++) {
            modelos[pos] = new Modelo();
        }
    }

    public void pedirDatos() {
        String nombre;
        int tipo;
        for (int nmodelo = 0; nmodelo < modelos.length; nmodelo++) {
            nombre = Textos.pedirString("Nombre del modelo: ");
            menu();
            tipo = Numero.pedirNumero("Introduce un tipo:",
                    0, tipos.length - 1);
            modelos[nmodelo].grabar(nombre, tipo);
        }
    }

    private void menu() {
        for (int tipo = 0; tipo < tipos.length; tipo++) {
            System.out.println(tipo + "\t" + tipos[tipo]);
        }
    }

    public void pedirVentasMes() {
        String[] meses = {"Enero",
            "Febrero", "Marzo", "Abril",
            "Mayo", "Junio"};
        //float[] ventas;

        float importe;
        for (int nModelo = 0; nModelo < modelos.length;
                nModelo++) {
            //ventas=new float[6];
            System.out.println("MODELO:" + modelos[nModelo].getNombre());
            System.out.println("Ventas");
            for (int mes = 0;
                    mes < modelos[nModelo].getVentas().length;
                    mes++) {
                importe = Numero.pedirNumeroReal(
                        meses[mes] + "\t", 0);
                modelos[nModelo].setUnaVenta(importe, mes);
                // ventas[mes]= Numero.pedirNumeroReal(
                //    meses[mes]+"\t",0);

            }
            // modelos[nModelo].setVentas(ventas);

        }
    }

    public void informe() {
        float total;
        float beneficio;
        int fila;
        int columna;
        int pagina=1;
        cabecera(pagina);
        int linea=3;
        for (int nmodelo = 0; nmodelo < modelos.length; nmodelo++) {
           if( linea==5){
                pagina++;
                cabecera(pagina);
                linea=3;
            }
            System.out.print(modelos[nmodelo].getNombre());
            System.out.print("\t\t" + modelos[nmodelo].ventasTrimestre(0));
            System.out.print("\t\t" + modelos[nmodelo].ventasTrimestre(3));
            total = modelos[nmodelo].ventasTrimestre(0)
                    + modelos[nmodelo].ventasTrimestre(3);
            System.out.print("\t\t" + total);
            fila = modelos[nmodelo].getTipo();
            columna = buscar(total);
            beneficio = beneficios[fila][columna] * total;
            System.out.println("\t" + beneficio);
            linea++;
            
        }
    }

    private int buscar(float importe) {
        int pos = 0;
        while (importe >= limites[pos]) {
            pos++;
        }
        /*
        pos=0; 
        boolean encontrado=false;
        while( !encontrado && pos< limites.length)
        {
            if ( importe< limites[pos])
                encontrado=true;
            else
                pos++;
            
        }
        if ( !encontrado)
            pos=-1;
         */
        return pos;
    }

    private void cabecera(int pagina) {
        Fecha fhoy = new Fecha();

        System.out.println("INFORME DE VENTAS");
        System.out.println("Fecha:" + fhoy.fechaCompletaLetra()
        +" Página:"+pagina);
        System.out.println("MODELO \t IMPORTE 1TRI\t "
                + "IMPORTE 2TRI \t TOTAL \t BENEFICIO");

    }
}
