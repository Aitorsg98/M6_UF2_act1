package uf2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Act1 {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/act1", "root", "");

			boolean sortir = false;
			int opcio = 0;

			while (!sortir) {
				
				stmt = connection.createStatement();
				
				System.out.println("Introdueix una opcio:");
				System.out.println("1.Afegir 2.Modificar 3.Eliminar 4.Sortir");
				opcio = scan.nextInt();

				if (opcio == 1) {
					afegirAlumne(stmt);
				} else if (opcio == 2) {

				} else if (opcio == 3) {

				} else if (opcio == 4) {

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

	public static void afegirAlumne(Statement stmt) throws SQLException {
		System.out.println("Introdueix un DNI");
		String dni = scan.next();
		System.out.println("Introdueix un nom");
		String nom = scan.next();
		System.out.println("Introdueix la data de naixement en el següent format any/mes/dia");
		String data = scan.next();
		System.out.println("Introdueix la adreça");
		String adreça = scan.next();
		System.out.println("Introdueix el codi postal");
		int codiPostal = scan.nextInt();
		scan.nextLine();
		System.out.println("Introdueix la poblacio");
		String poblacio = scan.next();
		stmt.execute("INSERT INTO alumnes VALUES ('" + dni + "', '" + nom + "', '" + data + "', '" + adreça + "', " 
		+ codiPostal + ", '" + poblacio + "')");
	}

	public static void sortir() {
		System.exit(0);
	}

}
