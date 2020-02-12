package uf2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Act1 {

	public static Scanner scan = new Scanner(System.in);
	public static String dni;
	public static String dni2;
	public static String nom;
	public static String data;
	public static String adreça;
	public static int codiPostal;
	public static int codiPostal2;
	public static int opcio;
	public static boolean sortir;
	public static Connection connection;
	public static Statement stmt;

	public static void main(String[] args) {
		// Declarem les variables que ens permetran executar sentències
		connection = null;
		stmt = null;
		
		// Ens connectem a la base de dades
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/act1", "root", "");

			sortir = false;

			while (!sortir) {

				stmt = connection.createStatement();

				// Menu on triarem l'opció que volem
				System.out.println("Introdueix una opcio:");
				System.out.println("1.Afegir 2.Modificar 3.Eliminar 4.Mostrar 5.Sortir");
				opcio = scan.nextInt();

				// Opció per inserir dades a una taula
				if (opcio == 1) {
					afegir();
				// Opció per modificar una fila a una taula
				} else if (opcio == 2) {
					modificar();
				// Opció per eliminar una fila a una taula
				} else if (opcio == 3) {
					eliminar();
				// Opció per mostrar les files d'una taula
				} else if (opcio == 4) {
					mostrar();
				// Opció per sortir del programa
				} else if (opcio == 5) {
					sortir();
				}

			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {   
				stmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void afegir() throws SQLException {

		while (!sortir) {

			// Triem si volem inserir una fila a la taula alumnes o poblacio
			System.out.println("Introdueix una opció");
			System.out.println("1.Afegir alumne 2.Afegir poblacio");
			opcio = scan.nextInt();

			// Opció per inserir una fila a la taula alumnes
			if (opcio == 1) {
				// Demanem les dades
				System.out.println("Introdueix un DNI");
				dni = scan.next();
				System.out.println("Introdueix un nom");
				nom = scan.next();
				System.out.println("Introdueix la data de naixement en el següent format any/mes/dia");
				data = scan.next();
				System.out.println("Introdueix la adreça");
				adreça = scan.next();
				System.out.println("Introdueix el codi postal");
				codiPostal = scan.nextInt();
				scan.nextLine();

				// Executem la sentència SQL
				stmt = connection.createStatement();
				stmt.execute("INSERT INTO alumnes VALUES ('" + dni + "', '" + nom + "', '" + data + "', '" + adreça + "', " 
						+ codiPostal + ")");
				sortir = true;

			// Opció per inserir una fila a la taula poblacio
			} else if (opcio == 2) {
				// Demanem les dades
				System.out.println("Introdueix el codi postal");
				codiPostal = scan.nextInt();
				System.out.println("Introdueix un nom");
				nom = scan.next();
				// Executem la sentència SQL
				stmt.execute("INSERT INTO poblacio VALUES (" + codiPostal + ", '" + nom + "')");
				sortir = true;
			}
		}
		sortir = false;
	}

	public static void modificar() throws SQLException {

		while (!sortir) {
			// Triem si volem modificar una fila a la taula alumnes o poblacio
			System.out.println("Introdueix una opcio");
			System.out.println("1.Modificar alumne 2.Modificar poblacio");
			opcio = scan.nextInt();

			// Opció per modificar una fila a la taula alumnes
			if (opcio == 1) {
				// Demanem les noves dades
				System.out.println("Introdueix el DNI actual");
				dni = scan.next();
				System.out.println("Introdueix el nou DNI");
				dni2 = scan.next();
				System.out.println("Introdueix un nom");
				nom = scan.next();
				System.out.println("Introdueix la data de naixement en el següent format any/mes/dia");
				data = scan.next();
				System.out.println("Introdueix la adreça");
				adreça = scan.next();
				System.out.println("Introdueix el codi postal");
				codiPostal = scan.nextInt();
				scan.nextLine();
				
				// Executem la sentència SQL
				stmt.execute("UPDATE alumnes SET DNI='" + dni2 + "', nom='" + nom + "', data_naixement='" + data + "', adreca_postal='"
						+ adreça + "', codi_postal=" + codiPostal + " WHERE DNI='" + dni + "'");
				sortir = true;

			// Opció per modificar una fila a la taula poblacio
			} else if (opcio == 2) {
				// Demanem les dades
				System.out.println("Introdueix el codi postal actual");
				codiPostal = scan.nextInt();
				System.out.println("Introdueix el nou codi postal");
				codiPostal2 = scan.nextInt();
				System.out.println("Introdueix un nom");
				nom = scan.next();
				
				// Executem la sentència SQL
				stmt.execute("UPDATE poblacio SET codi_postal=" + codiPostal2 + ", nom='" + nom + "' WHERE codi_postal=" + codiPostal);
				sortir = true;
			}
		}
		sortir = false;
	}

	public static void eliminar() throws SQLException {

		while (!sortir) {
			// Triem si volem eliminar una fila de la taula alumnes o poblacio mitjançant el codi postal
			System.out.println("Introdueix una opcio");
			System.out.println("1.Eliminar alumne 2.Eliminar poblacio");
			opcio = scan.nextInt();

			// Opció per eliminar una fila de la taula alumnes
			if (opcio == 1) {
				// Demanem el codi postal
				System.out.println("Introdueix el DNI");
				dni = scan.next();
				
				// Executem la sentència SQL
				stmt.execute("DELETE FROM alumnes WHERE DNI='" + dni + "'");
				sortir = true;

			// Opció per eliminar una fila de la taula poblacio
			} else if (opcio == 2) {
				// Demanem el codi postal
				System.out.println("Introdueix el codi postal");
				codiPostal = scan.nextInt();
				
				// Executem la sentència SQL
				stmt.execute("DELETE FROM poblacio WHERE codi_postal=" + codiPostal);
				sortir = true;
			}
		}
		sortir = false;
	}

	public static void mostrar() throws SQLException {

		while(!sortir) {
			// Triem si volem mostrar les files de la taula alumnes o poblacio
			System.out.println("Introdueix una opcio");
			System.out.println("1.Mostrar alumnes 2.Mostrar poblacions");
			opcio = scan.nextInt();

			// Opció per mostrar les files de la taula alumnes
			if (opcio == 1) {
				// Executem la sentència
				ResultSet rs = stmt.executeQuery("SELECT * FROM alumnes");

				// Recorrem les files
				while (rs.next()) {

					// Mostrem les dades
					System.out.println("---------------------------------------------------------------");
					System.out.println("DNI " + rs.getString(1));
					System.out.println("nom " + rs.getString(2));
					System.out.println("data_naixement " + rs.getString(3));
					System.out.println("adreca_postal " + rs.getString(4));
					System.out.println("codi_postal " + rs.getString(5));

				}
				System.out.println("---------------------------------------------------------------");
				sortir = true;

			// Opció per mostrar les files de la taula poblacio
			} else if (opcio == 2) {
				// Executem la sentència SQL
				ResultSet rs = stmt.executeQuery("SELECT * FROM poblacio");

				// Recorrem les files de la taula poblacio
				while (rs.next()) {

					// Mostrem les dades
					System.out.println("---------------------------------------------------------------");
					System.out.println("codi_postal " + rs.getString(1));
					System.out.println("nom " + rs.getString(2));

				}
				System.out.println("---------------------------------------------------------------");
				sortir = true;
			}
		}
		sortir = false;
	}

	public static void sortir() throws SQLException {
		// Sortim del programa
		connection.close();
		System.exit(0);
	}

}
