package Programas.EjerciciosTareas;

import java.util.Scanner;



    public class RRRRR1 {

        public static void main(String[] args) {
            String [] tipoT = {"V","V","V","V","V","V","A","A","C","G"};
            int [] sueldoBase = {1025,1025,1025,1025,1025,1025,1025, 1025,1500,2000};
            int [] ventasA =  {2,1,2,1,2,1};
            int [] ventasB =  {1,2,0,1,1,2};
            int PPA = 17000;
            int PPB = 14000;
            int UIT = 4950;
            String [] aFP = {"S", "S", "S", "S", "S", "S","N", "N", "S","S"};
            String [] ePS = {"S", "S", "S", "S", "S", "S","N", "N", "S","S"};
            Scanner input = new Scanner(System.in);
            int []tardanzas =      {0,10,2,0,1,4,8,3,0,3};
            int [] inasistencias = {0,2,0,0,0,1,0,2,0,1,0};
            String [] trabajadores = {"Pedro", "Luis", "Juan", "Abel", "Rosa", "Ivan", "Graciela", "Eladio", "Jaime", "Josue"};
            int cuotaEPS = 200;
            String trabaj;

            System.out.println("Ingrese el nombre del trabajador del que desea calcular el sueldo");
            trabaj = input.nextLine();

        /*for (int i = 0; i < sueldoBase.length ; i++) {
            int posicion = i;
            System.out.println("Ingrese los datos de: " + trabajadores[i]);
            System.out.println("Ingrese unidades vendidad del vehículo A por los colaboradores");
            ventasA[i] = input.nextInt();
            System.out.println("Ingrese unidades vendidad del vehículo B por los colaboradores");
            ventasB[i] = input.nextInt();
            System.out.println("Ingrese el número de tardanzas del colaborador en el mes");
            tardanzas[i] = input.nextInt();
            System.out.println("Ingrese el número de inasistencias del colaborador en el mes");
            inasistencias[i] = input.nextInt();
        }
         */

            System.out.println("" +trabaj + " es " + tipodeVendedor(trabaj,tipoT,trabajadores));
            System.out.println("Su sueldo base es de S/ " + mostrarSueldoBase(trabaj,trabajadores,sueldoBase));
            System.out.printf("Su comisión es de S/ %.2f \n", calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB,PPA,PPB,tipoT,sueldoBase));
            System.out.printf("Su descuento por impuesto a la renta es S/ %.2f \n", calculoImpuestoRenta(trabaj,trabajadores,ventasA,ventasB,PPA,PPB,sueldoBase,UIT,tipoT));
            System.out.printf("El descuento de salud es de S/ %.2f \n", descuentoporPrestacionesSalud(trabaj,trabajadores,ePS,sueldoBase,cuotaEPS));
            System.out.printf("El descuento por tu plan de pensión es de S/ %.2f \n", descuentoporPlandePension(trabaj,trabajadores,aFP,sueldoBase));
            System.out.printf("El descuento por tardanzas es de S/ %.2f \n", descuentoportardanzas(trabaj,trabajadores,sueldoBase,tardanzas));
            System.out.printf("El descuento por inasistencias es de %.2f \n", descuentoporinasistencias(trabaj,trabajadores,sueldoBase,inasistencias));
            System.out.printf("El sueldo de " + trabaj + " este mes es de S/ %.2f \n", calculoSueldo(trabaj,trabajadores,ventasA,ventasB,PPA,PPB,sueldoBase,UIT,aFP,ePS,tardanzas,inasistencias,tipoT,cuotaEPS));

        }

        public static String tipodeVendedor (String trabaj, String [] tipoT, String [] trabajadores){
            String tipoTrab = null;
            for (int i = 0; i < trabajadores.length; i++){
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
        public static double calculoComisionyBonificacion (String trabaj, String [] trabajadores, int[] ventasA, int [] ventasB, int PPA, int PPB, String [] tipoT, int[]sueldoBase){
            double comision = 0;
            int sumaA = 0;
            int sumaB = 0;
            if (trabaj.equals("Josue")) {
                for (int i = 0; i < ventasA.length; i++) {
                    sumaA = sumaA + ventasA[i];
                    sumaB = sumaB + ventasB[i];
                }
                comision = (sumaA * PPA * 0.01) + (sumaB * PPB * 0.01);
            } else {

                for (int i = 0; i < ventasA.length; i++) {
                    if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("V")) {
                        comision = (((ventasA[i] * PPA) * 0.03) + ((ventasB[i] * PPB) * 0.035));
                    } else if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("A")) {
                        comision = sueldoBase[i] * 0.15;
                    } else if (trabaj.equals(trabajadores[i]) && tipoT[i].equals("C")) {
                        comision = sueldoBase[i] * 0.20;
                    }
                }

            }
            return comision;
        }

        public static double calculoImpuestoRenta (String trabaj, String [] trabajadores, int[] ventasA, int [] ventasB, int PPA, int PPB, int [] sueldoBase, int UIT, String[]tipoT){
            double sueldo = mostrarSueldoBase(trabaj,trabajadores,sueldoBase);
            double comis = calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB,PPA,PPB,tipoT,sueldoBase);
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

        public static double descuentoportardanzas (String trabaj, String [] trabajadores, int [] sueldoBase, int [] tardanzas){
            double dctotarda = 0;

            for (int i = 0; i < trabajadores.length; i++) {
                if (trabaj.equals(trabajadores[i])) {
                    dctotarda = (tardanzas[i] * (sueldoBase[i] * 0.004));
                }

            }
            return dctotarda;
        }
        public static double descuentoporinasistencias (String trabaj, String [] trabajadores, int [] sueldoBase, int [] inasistencias) {
            double dctoinas = 0;
            for (int i = 0; i < trabajadores.length; i++) {
                if (trabaj.equals(trabajadores[i])) {
                    dctoinas = (inasistencias[i] * sueldoBase[i] * 0.004);
                }
            }
            return dctoinas;

        }
        public static double calculoSueldo (String trabaj, String [] trabajadores, int[] ventasA, int [] ventasB, int PPA, int PPB, int [] sueldoBase, int UIT, String[]aFP, String [] ePS, int []tardanzas, int []inasistencias, String []tipoT, int cuotaEPS){
            double sueldohab = 0;
            double sueldodesc = 0;
            double sueldo = 0;
            for (int i = 0; i < trabajadores.length; i++) {
                if (trabaj.equals(trabajadores[i])) {
                    sueldohab = (mostrarSueldoBase(trabaj, trabajadores, sueldoBase) + calculoComisionyBonificacion(trabaj, trabajadores, ventasA, ventasB, PPA, PPB, tipoT, sueldoBase));
                    sueldodesc = (calculoImpuestoRenta(trabaj, trabajadores, ventasA, ventasB, PPA, PPB, sueldoBase, UIT, tipoT) + descuentoporPrestacionesSalud(trabaj, trabajadores, ePS, sueldoBase, cuotaEPS) + descuentoporPlandePension(trabaj, trabajadores, aFP, sueldoBase) + descuentoportardanzas(trabaj, trabajadores, sueldoBase, tardanzas) + descuentoporinasistencias(trabaj, trabajadores, sueldoBase, inasistencias));
                    sueldo = sueldohab - sueldodesc;
                }
            }
            return sueldo;
        }
    }


