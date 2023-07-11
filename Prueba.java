package Programas.EjerciciosTareas;

import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {

        String[] tipoT = {"Vendedor", "Vendedor", "Vendedor", "Vendedor", "Vendedor", "Vendedor", "Apoyo", "Apoyo", "Contador", "Gerente"};
        int[] sueldoBase = {1025, 1025, 1025, 1025, 1025, 1025, 1025, 1025, 1500, 2000};
        int []ventasA = {0,0,0,0,0,0};
        int [] ventasB = {0,0,0,0,0,0};
        int PPA = 17000;
        int PPB = 14000;
        int UIT = 4950;
        String[] aFP = {"S", "S", "S", "S", "S", "S", "N", "N", "S", "S"};
        String[] ePS = {"S", "S", "S", "S", "S", "S", "N", "N", "S", "S"};
        Scanner input = new Scanner(System.in);
        int[] tardanzas = {0,0,0,0,0,0,0,0,0,0};
        int[] inasistencias = new int[10];
        String[] trabajadores = {"Pedro", "Luis", "Juan", "Abel", "Rosa", "Ivan", "Graciela", "Eladio", "Jaime", "Josue"};
        int cuotaEPS = 200;
        int ventasMesA = 0;
        int ventasMesB = 0;
        String trabaj;
        

        System.out.println("Vamos a empezar a calcular los sueldos de los colaboradores de MG Perú");

        System.out.println("Ingrese el nombre del trabajador del que desea calcular el sueldo");
        trabaj = input.nextLine();

        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Vendedor")) {
                while (ventasA[i] == 0 && ventasB[i] == 0) {
                    System.out.println("Ingrese los datos de: " + trabajadores[i]);
                    System.out.println("Cuántos vehículos de tipo A ha vendido en el mes? " + trabajadores[i] + " en el mes");
                    ventasA[i] = input.nextInt();
                    System.out.println("Cuántos vehículos tipo A ha vendido en el mes? " + trabajadores[i] + " en el mes");
                    ventasB[i] = input.nextInt();
                    System.out.println("Ingrese el número de tardanzas de " + trabajadores[i] + " en el mes");
                    tardanzas[i] = input.nextInt();
                    System.out.println("Ingrese el número de inasistencias de " + trabajadores[i] + " en el mes");
                    inasistencias[i] = input.nextInt();
                }
            } else if ((trabaj.equals(trabajadores[i]) && tipoT[i].equals("Apoyo")) || (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Contador"))) {
                while (tardanzas[i] == 0 && inasistencias[i]== 0) {
                    System.out.println("Ingrese los datos de: " + trabajadores[i]);
                    System.out.println("Ingrese el número de tardanzas de " + trabajadores[i] + " en el mes");
                    tardanzas[i] = input.nextInt();
                    System.out.println("Ingrese el número de inasistencias de " + trabajadores[i] + " en el mes");
                    inasistencias[i] = input.nextInt();
                }
            } else if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Gerente")) {
                while (ventasMesA == 0) {
                    System.out.println("Ingrese los datos de: " + trabajadores[i]);
                    System.out.println("Cuántos vehículos del tipo A se han vendido en el mes");
                    ventasMesA = input.nextInt();
                    System.out.println("Cuántos vehículos del tipo B se han vendido en el mes");
                    ventasMesB = input.nextInt();
                    System.out.println("Ingrese el número de tardanzas de " + trabajadores[i] + " en el mes");
                    tardanzas[i] = input.nextInt();
                    System.out.println("Ingrese el número de inasistencias de " + trabajadores[i] + " en el mes");
                    inasistencias[i] = input.nextInt();
                }
            }
        }

        System.out.println("" + trabaj + " es " + tipoVendedor(trabaj,tipoT,trabajadores));
        System.out.println("Su sueldo base es de S/ " + mostrarSueldoBase(trabaj,trabajadores,sueldoBase));
        System.out.printf("Su comisión es de S/ %.2f \n", calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB,PPA,PPB,tipoT,sueldoBase,ventasMesA,ventasMesB));
        System.out.printf("Su descuento por impuesto a la renta es S/ %.2f \n", calculoImpuestoRenta(trabaj,trabajadores,ventasA,ventasB,PPA,PPB,sueldoBase,UIT,tipoT,ventasMesA,ventasMesB));
        System.out.printf("El descuento por prestaciones de salud es de S/ %.2f \n", descuentoporPrestacionesSalud(trabaj,trabajadores,ePS,sueldoBase,cuotaEPS));
        System.out.printf("El descuento por tu plan de pensión es de S/ %.2f \n", descuentoporPlandePension(trabaj,trabajadores,aFP,sueldoBase));
        System.out.printf("El descuento por tardanzas es de S/ %.2f \n", descuentoportardanzas(trabaj,trabajadores,sueldoBase,tardanzas, ventasA, ventasB, PPA, PPB, tipoT, ventasMesA, ventasMesB));
        System.out.printf("El descuento por inasistencias es de S/ %.2f \n", descuentoporinasistencias(trabaj,trabajadores,sueldoBase,inasistencias, ventasA, ventasB, PPA, PPB, tipoT, ventasMesA, ventasMesB));
        System.out.printf("El sueldo de " + trabaj + " este mes es de S/ %.2f \n", calculoSueldo(trabaj,trabajadores,ventasA,ventasB,PPA,PPB,sueldoBase,UIT,aFP,ePS,tardanzas,inasistencias,tipoT,cuotaEPS,ventasMesA,ventasMesB));

    }

    public static String tipoVendedor(String trabaj, String[] tipoT, String[] trabajadores) {
        String tipoTrab = null;
        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i])) {
                tipoTrab = tipoT[i];
            }
        }
        return tipoTrab;
    }

    public static int mostrarSueldoBase (String trabaj, String [] trabajadores, int [] sueldoBase){
        int sueldoB = 0;
        for (int i = 0; i < trabajadores.length; i++){
            if (trabaj.equals(trabajadores[i]))
                sueldoB = sueldoBase[i];

        }

        return sueldoB;
    }

    public static double calculoComisionyBonificacion (String trabaj, String [] trabajadores, int[] ventasA, int [] ventasB, int PPA, int PPB, String [] tipoT, int[]sueldoBase, int ventasMesA, int ventasMesB){
        double comision = 0;

        for (int i = 0; i < trabajadores.length; i++){
            if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Vendedor")){
                comision = (((ventasA[i]*PPA)*0.03) + ((ventasB[i]*PPB)*0.035));
            }else if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Apoyo")){
                comision = sueldoBase[i]*0.15;
            } else if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Contador")){
                comision = sueldoBase[i]*0.20;
            } else if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("Gerente")){
                comision = (ventasMesA * PPA * 0.01) + (ventasMesB * PPB * 0.01);
            }
        }
        return comision;
    }
    public static double calculoImpuestoRenta (String trabaj, String [] trabajadores, int[] ventasA, int [] ventasB, int PPA, int PPB, int [] sueldoBase, int UIT, String[]tipoT, int ventasMesA, int ventasMesB){
        double sueldo = mostrarSueldoBase(trabaj,trabajadores,sueldoBase);
        double comis = calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB,PPA,PPB,tipoT,sueldoBase,ventasMesA,ventasMesB);
        double renta = (((((sueldo + comis) * 14) - (7 * UIT)) * 0.08) / 12);
        if (renta <= 0){
            renta = 0;
        }
        return renta;
    }

    public static double descuentoporPrestacionesSalud (String trabaj, String [] trabajadores, String[] ePS, int [] sueldoBase, int cuotaEPS){
        double dctosalud = 0;
        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i]))
                if (ePS[i].equals("S")) {
                    dctosalud = ((cuotaEPS) - (sueldoBase[i] * 0.09));
                } else {
                    dctosalud = 0;
                }
        }
        return dctosalud;
    }

    public static double descuentoporPlandePension (String trabaj, String [] trabajadores, String[] aFP, int [] sueldoBase){
        double dctopension = 0;
        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i]))
                if (aFP[i].equals("S")) {
                    dctopension = (sueldoBase[i] * 0.10);
                } else {
                    dctopension = (sueldoBase[i] * 0.13);
                }
        }
        return dctopension;
    }
    public static double descuentoportardanzas (String trabaj, String [] trabajadores, int [] sueldoBase, int [] tardanzas, int[] ventasA, int [] ventasB, int PPA, int PPB, String [] tipoT, int ventasMesA, int ventasMesB){
        double dctotarda = 0;
        double com = calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB, PPA, PPB, tipoT, sueldoBase, ventasMesA, ventasMesB);

        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i])) {
                dctotarda = (tardanzas[i] * ((sueldoBase[i] + com) * 0.004));
            }

        }
        return dctotarda;
    }

    public static double descuentoporinasistencias (String trabaj, String [] trabajadores, int [] sueldoBase, int [] inasistencias, int[] ventasA, int [] ventasB, int PPA, int PPB, String [] tipoT, int ventasMesA, int ventasMesB){
        double dctotarda = 0;
        double com = calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB, PPA, PPB, tipoT, sueldoBase, ventasMesA, ventasMesB);

        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i])) {
                dctotarda = (inasistencias[i] * ((sueldoBase[i] + com) * 0.04));
            }

        }
        return dctotarda;
    }

    public static double calculoSueldo (String trabaj, String [] trabajadores, int[] ventasA, int [] ventasB, int PPA, int PPB, int [] sueldoBase, int UIT, String[]aFP, String [] ePS, int []tardanzas, int []inasistencias, String []tipoT, int cuotaEPS, int ventasMesA,int ventasMesB){
        double sueldohab = 0;
        double sueldodesc = 0;
        double sueldo = 0;
        for (int i = 0; i < trabajadores.length; i++) {
            if (trabaj.equals(trabajadores[i])) {
                sueldohab = (mostrarSueldoBase(trabaj, trabajadores, sueldoBase) + calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB,PPA,PPB,tipoT,sueldoBase,ventasMesA,ventasMesB));
                sueldodesc = (calculoImpuestoRenta(trabaj,trabajadores,ventasA,ventasB,PPA,PPB,sueldoBase,UIT,tipoT,ventasMesA,ventasMesB) + descuentoporPrestacionesSalud(trabaj, trabajadores, ePS, sueldoBase, cuotaEPS) + descuentoporPlandePension(trabaj, trabajadores, aFP, sueldoBase) + descuentoportardanzas(trabaj,trabajadores,sueldoBase,tardanzas, ventasA, ventasB, PPA, PPB, tipoT, ventasMesA, ventasMesB) + descuentoporinasistencias(trabaj,trabajadores,sueldoBase,inasistencias, ventasA, ventasB, PPA, PPB, tipoT, ventasMesA, ventasMesB));
                sueldo = sueldohab - sueldodesc;
            }
        }
        return sueldo;
    }
}





