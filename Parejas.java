package JUEGOPAREJAS;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Parejas {
	//	Juego de las parejas. Se crea una matriz de 7x4 para 28 cartas y 14 parejas.
	//	Se carga el array con parejas de números del 1 al 14. Barajeo dichos valores.
	//	Solicitar sucesivamente a dos jugadores las cartas a levantar. Sin son iguales los
	//	puntuamos al jugador respectivo. El juego finaliza cuando todas las cartas estén
	//	“levantadas”.
	public static void main(String[] args) {
		int[][] aMatriz = new int[7][4];
		rellenarParejas(aMatriz);
		System.out.println("\n\tMatriz generada, esta es su solución: ");
		leerArray(aMatriz);
		System.out.println("\n\tIntente resolverla por su cuenta: ");
		jugarParejas(aMatriz);
	}

	public static void rellenarParejas(int[][] aArray) {
		// Funcion pensada unicamente para el juego de adivinar parejas
		// El array que llegue debe ser par, para que se puedan hacer las parejas
		// Puede generar parejas independientemente de la longitud del array,
		// siempre y cuando el resultado de las filas * columnas del array
		// sea par.
		int num = 1;
		final int tope = (int) ((aArray.length * aArray[0].length) / 2) + 1;
		int cont = 0;
		while (num != tope) {
			int azarX = (int) (Math.random() * aArray.length);
			int azarY = (int) (Math.random() * aArray[0].length);
			if (aArray[azarX][azarY] == 0) {
				aArray[azarX][azarY] = num;
				cont++;
				if (cont >= 2 && cont % 2 == 0) {
					num++;
				}
			}
		}
	}

	public static void jugarParejas(int[][] aArray) {
		// Juego de adivinar parejas
		// Las posiciones están simplificadas, el usuario no tiene que saber que
		// internamente
		// el array empieza en 0, para el empieza en 1 y acaba en 4 en el caso de las
		// columnas
		// Si introduce una posición incorrecta se repetirá el codigo y no saldrá ningún
		// error
		// por pantalla gracias al bloque try catch
		// Se crea un nuevo Array con las mismas dimensiones que el original para hacer
		// la "animacion" de ir levantando cartas
		// El programa finaliza cuando todas las cartas son levantadas, es decir,
		// cuando se encuentra su respectiva pareja
		// Al intentar revelar una posición que ya está revelada el programada le
		// avisará usuario y volvera a pedir todas las posiciones de ese turno
		int posFila1 = 0, posColumna1 = 0, posFila2, posColumna2;
		int cont = 0;
		int intentos = 0;
		int[][] nuevoArray = new int[aArray.length][aArray[0].length];
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\tTenga en cuenta que la matriz va desde la fila 1 hasta la 7 "
				+ "y desde la columna 1 hasta la 4. ");
		System.out.println("\n\t---ADIVINA LAS PAREJAS---");
		while (cont != 14) {
			try {
				leerArray(nuevoArray);
				System.out.println("\n\tIntroduza una posicion (fila1): ");
				posFila1 = sc.nextInt();
				System.out.println("\n\tIntroduza una posicion (columna1): ");
				posColumna1 = sc.nextInt();
				if (nuevoArray[posFila1 - 1][posColumna1 - 1] != 0) {
					System.out.println("\n\tEsta posición ya está revelada, inserte otra posición. ");
					continue;
				}
				nuevoArray[posFila1 - 1][posColumna1 - 1] = aArray[posFila1 - 1][posColumna1 - 1];
				leerArray(nuevoArray);
				System.out.println("\n\tIntroduza una posicion (fila2): ");
				posFila2 = sc.nextInt();
				System.out.println("\n\tIntroduza una posicion (columna2): ");
				posColumna2 = sc.nextInt();
				if (nuevoArray[posFila2 - 1][posColumna2 - 1] != 0) {
					System.out.println("\n\tEsta posición ya está revelada, inserte otras posiciones. ");
					nuevoArray[posFila1 - 1][posColumna1 - 1] = 0;
					continue;
				}
				nuevoArray[posFila2 - 1][posColumna2 - 1] = aArray[posFila2 - 1][posColumna2 - 1];
				leerArray(nuevoArray);
				if (aArray[posFila1 - 1][posColumna1 - 1] == aArray[posFila2 - 1][posColumna2 - 1]) {
					System.out.println("\n\tFelicidades, has econtrado la pareja del numero: "
							+ aArray[posFila1 - 1][posColumna1 - 1]);
					cont++;
				} else {
					System.out.println("\n\tMala suerte, no son parejas, vuelve a intentarlo. ");
					intentos++;
					System.out.println("\tLlevas " + intentos + " intentos fallidos.");
					nuevoArray[posFila1 - 1][posColumna1 - 1] = 0;
					nuevoArray[posFila2 - 1][posColumna2 - 1] = 0;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("\n\tPosición fuera de la matriz, posición incorrecta, vuelva a intentarlo.");
				if (posFila1 != 0 && posColumna1 != 0 && posFila1 < aArray.length && posColumna1 < aArray[0].length) {
					nuevoArray[posFila1 - 1][posColumna1 - 1] = 0;
				}
				continue;
			} catch (InputMismatchException e) {
				int conta = 0;
				System.out.println("\n\tCarácter incorrecto, introduzca un número por favor.");
				if (posFila1 != 0 && posColumna1 != 0 && posFila1 < aArray.length && posColumna1 < aArray[0].length) {
					for (int i = 0; i < aArray.length; i++) {
						for (int j = 0; j < aArray[0].length; j++) {
							if (nuevoArray[i][j] == nuevoArray[posFila1][posColumna1]) {
								conta++;
							}
						}
					}
					if (conta == 2) {
						sc.next();
					} else {
						nuevoArray[posFila1 - 1][posColumna1 - 1] = 0;
					}
				}
				sc.next();
			}
		}
		sc.close();
		System.out.println("\n\tMatriz completada! Felicidades!");
		System.out.println("\n\tHas tenido un total de " + intentos + " erroneos.");
	}

	public static void leerArray(int[][] aArray) {
		// Lee el array que se le pasa, independientemente de su longitud
		System.out.println();
		for (int i = 0; i < aArray.length; i++) {
			System.out.println();
			for (int j = 0; j < aArray[0].length; j++) {
				System.out.print("\t" + aArray[i][j] + " ");
			}
		}
		System.out.println();
	}
}
