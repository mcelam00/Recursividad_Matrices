import java.util.Scanner;

public class MatrizRaízEnteraSimétrica {

	public static void main(String[] args) {

		Scanner leerTeclado = new Scanner(System.in);
		
		int orden;
		String cadena;
		int matriz[][];
		int contadorFilas;
		int mCuadrada[][];
		int mTraspuesta[][];
		boolean iguales;
		
		
		orden = 0;
		contadorFilas = 0;
		
		System.out.print("Por favor, introduzca el orden de la matríz: ");
		cadena = leerTeclado.nextLine(); //lo pongo con nextline para así tratar el caso en el que meta 2 5, porque solo se debe meter un número
		
		
		
		try {
			
			orden = Integer.parseInt(cadena); //si trata de hacer el parseado a una cadena que tiene espacios no va a poder
			
		}catch(Exception errorTeclado) {
			
			System.out.println("Entrada Inválida.");
			System.exit(0);
		
		}
		
		
		if (orden < 0) {
			
			System.out.println("Entrada Inválida.");
			System.exit(0);
			
		}
		
		//ya he leido el orden de la matríz
		
		
		//Voy a leer la matriz
		matriz = new int [orden][orden]; //he creado la matriz a rellenar
		
		

		matriz = recorrerFilas(contadorFilas, orden, matriz);
		
		System.out.println("La matríz introducida es:");

		
		for(int i = 0; i < orden ; i++) {
			for(int j = 0; j < orden; j++) {
				
				System.out.print(matriz[i][j]+" ");

				
			}
			System.out.println();
		}
		
		//calculo la matriz cuadrada
		mCuadrada = new int[orden][orden];
		
		mCuadrada = recorrerMatriz(matriz, contadorFilas, orden); //OJO! A VER, NO LE PUEDO PASAR LA MATRIZ NUEVA, QUE ESTA VACIA!!! HAY QUE PASARLE LA LEIDA
		
		System.out.println("Su matríz cuadrada entera (La raíz cuadrada entera de cada uno de los números) es:");

		
		for(int i = 0; i < orden ; i++) {
			for(int j = 0; j < orden; j++) {
				
				System.out.print(mCuadrada[i][j]+" ");

				
			}
			System.out.println();
		}
		
		//calculo la traspuesta
		mTraspuesta = new int[orden][orden]; //TENGO QUE USAR 2 MATRICES DISTINTAS LA VACIA EN LA QUE METO LO DE LA CUADRADA Y LA CUADRADA PARA COPIAR A LA OTRA
		
		mTraspuesta = calcularTraspuestaFil(contadorFilas, mCuadrada, orden, mTraspuesta);
		
		System.out.println("La matríz traspuesta de la cuadrada es:");

		
		for(int i = 0; i < orden ; i++) {
			for(int j = 0; j < orden; j++) {
				
				System.out.print(mTraspuesta[i][j]+" ");

				
			}
			System.out.println();
		}
		
		
		//comparo la traspuesta y la cuadrada
		iguales = true; //por defecto asumo que son iguales y asi solo tengo que poner el if de si son diferentes cambia a false
		
		iguales = compararMatricesFil(contadorFilas, orden, mCuadrada, mTraspuesta, iguales); 
		
		
		//digo si es simetrica o no
		
		if(iguales == true) {
			System.out.println("La matriz de tamaño "+orden+" es de raíz entera simétrica.");
		}
		else
		{
			System.out.println("La matriz de tamaño "+orden+" es de raíz entera no simétrica.");
		}
		
		System.out.println("NOTA: Una matríz es simétrica cuando es igual a su traspuesta");

		
		
		
		
		
		
		
		leerTeclado.close();
	}

	private static boolean compararMatricesFil(int contadorFilas, int orden, int[][] mCuadrada, int[][] mTraspuesta, boolean iguales) {
		//boolean iguales; no puedo ponerlo aqui tampoco porque si lo hago me resetea el testigo en cada fila y pierdo si en la anterior habia iguales
		int contadorCol;
		contadorCol = 0;
		//iguales = true; 
		
		
		if(contadorFilas == orden) {
			
			return iguales;
			
		}
		else
		{
			
			iguales = compararMatricesCol(mCuadrada, mTraspuesta, iguales, orden, contadorFilas, contadorCol); //DUDA? SE LO PASO COMO PARAMETRO O HAGO 2?; si lo pongo nuevo cada vez que cambie de columna me lo actualizaría a true, y no puede ser porque me pierde si en la columna anterior eran distintos
			return compararMatricesFil(contadorFilas+1, orden, mCuadrada, mTraspuesta, iguales);
			
		}
		
	}

	private static boolean compararMatricesCol(int[][] mCuadrada, int[][] mTraspuesta, boolean iguales, int orden, int contadorFilas, int contadorCol) {
		//boolean testigo;
		//testigo = true;
		
			if(contadorCol == orden) {
				return iguales;
				
			}
			else
			{
				if(mCuadrada[contadorFilas][contadorCol] != mTraspuesta[contadorFilas][contadorCol]) {
					iguales = false;
				}
				
				return compararMatricesCol(mCuadrada, mTraspuesta, iguales, orden, contadorFilas, contadorCol+1);
			}
	
	
	
	
	
	
	
	
	}

	private static int[][] calcularTraspuestaFil(int contadorFilas, int[][] mCuadrada, int orden, int[][] mTraspuesta) {
			
			int contadorColumnas;
			contadorColumnas = 0;
		
			if(contadorFilas == orden) {
				return mTraspuesta;
				
			}
			else
			{
				mTraspuesta = calcularTraspuestaCol(contadorFilas, contadorColumnas, orden, mCuadrada, mTraspuesta);
				return calcularTraspuestaFil(contadorFilas+1, mCuadrada, orden, mTraspuesta);
				
			}
		
		
		
		
		
		
		
	}

	private static int[][] calcularTraspuestaCol(int contadorFilas, int contadorColumnas, int orden, int[][] mCuadrada, int[][] mTraspuesta) {

			if(contadorColumnas == orden) {
				return mTraspuesta;
				
			}
			else
			{
				mTraspuesta[contadorFilas][contadorColumnas] = mCuadrada[contadorColumnas][contadorFilas];
				return calcularTraspuestaCol(contadorFilas, contadorColumnas+1, orden, mCuadrada, mTraspuesta);
			}
		
		
		
	}

	private static int[][] recorrerMatriz(int[][] mCuadrada, int contadorFilas, int orden) {
		int contadorColumnas;
		contadorColumnas = 0;
		
		if(contadorFilas == orden) {
			return mCuadrada;
		}
		else
		{
			mCuadrada = recorrerColumnas(mCuadrada, orden, contadorFilas, contadorColumnas);
			return recorrerMatriz(mCuadrada, contadorFilas+1, orden);
			
			
		}
		
		
	}

	private static int[][] recorrerColumnas(int[][] mCuadrada, int orden, int contadorFilas, int contadorColumnas) {
		
		int contador;
		int raizCuadrada;
		contador = 0;
		raizCuadrada = 0;
		
		if(contadorColumnas == orden) {
			return mCuadrada;		
			
		}
		else
		{
			raizCuadrada = hallarRaizCuadrada(mCuadrada, contadorFilas, contadorColumnas, contador); //me vuelve aquí la raíz cuadrada de la fila y la columna
			
			//tengo que guardarla en la matriz antes de aumentar columna
			
			mCuadrada[contadorFilas][contadorColumnas] = raizCuadrada;
			
			
			return recorrerColumnas(mCuadrada, orden, contadorFilas, contadorColumnas+1);
			
		}
	}

	private static int hallarRaizCuadrada(int[][] mCuadrada, int contadorFilas, int contadorColumnas, int contador) {

		if((contador*contador) == mCuadrada[contadorFilas][contadorColumnas]) { //es una raíz exacta
			
			return (contador);
			
		}
		else if((contador*contador) > mCuadrada[contadorFilas][contadorColumnas]) {
			
			return (contador-1);
		}
		else 
		{
			
			return hallarRaizCuadrada(mCuadrada, contadorFilas, contadorColumnas, contador+1);
			
		}
		
	
	}

	private static int[][] recorrerFilas(int contadorFilas, int orden, int[][] matriz) {
		int contadorColumnas;
		contadorColumnas = 0;
		
		if(contadorFilas == orden) {
			return matriz;
		}
		else
		{
			
			
			String cadena;
			String fila[];
			Scanner leerTeclado1 = new Scanner(System.in); //miro que lo que leo sea lo que es y no cosas raras
			
			System.out.println("Por favor introduzca la fila "+contadorFilas+" de la matríz");
			cadena = leerTeclado1.nextLine(); //leo una linea 5 7 
			
			
			
			fila = cadena.split(" "); //y la divido como 5,7
			if(fila.length != orden) { //si me mete espacios?
				System.out.println("Entrada Inválida.");
				System.exit(0);
			}
		//	leerTeclado1.close(); //POR ESTO DA LA JAVA UTILNOSUCHEXCEPTION
			
			
			matriz = recorrerColumnas(contadorColumnas, contadorFilas, orden, matriz, fila);
			return recorrerFilas(contadorFilas+1, orden, matriz);
		}
		
		


		
		
		
	}

	private static int[][] recorrerColumnas(int contadorColumnas, int contadorFilas, int orden, int[][] matriz, String[] fila) {

		if (contadorColumnas == orden) {
			return matriz;
		}
		else
		{
			matriz = leerPosicion(matriz, contadorFilas, contadorColumnas, orden, fila);
			
			return recorrerColumnas(contadorColumnas+1, contadorFilas, orden, matriz, fila);
		}
		
		
	}

	private static int[][] leerPosicion(int[][] matriz, int contadorFilas, int contadorColumnas, int orden, String[] fila) {
			
		
		
		matriz[contadorFilas][contadorColumnas] = Integer.parseInt(fila[contadorColumnas]); //fijemonos bien que una vez traducidos los numeros a entero, debe coincidir la columna con la posicion del arra y porque es como que lo estoy cortando y pegando del array a la matriz
		
		
		
		return matriz;
	}

}
