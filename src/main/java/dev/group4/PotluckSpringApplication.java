package dev.group4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@ComponentScan(basePackages = "dev.group4") // Tell spring to scan all code in the dev.ranieri packages
@EntityScan(basePackages = "dev.group4.entities") // tell spring to scan my entities
@EnableJpaRepositories(basePackages = "dev.group4.repos")// tell spring where to find my repos
/*
 * Potluck Tracker - Spring Application
 */
public class PotluckSpringApplication {

	public static void main(String[] args) {


		/*
		Server server = null;
		try {
			server = Server.createTcpServer("-tcpAllowOthers").start();
			Class.forName("org.h2.Driver");
			System.out.println(System.getProperty("user.dir").substring(3));
			Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/potluckdb", "sa","password");
			System.out.println(conn.getMetaData().getDatabaseProductName() + conn.getCatalog());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		*/
		SpringApplication.run(PotluckSpringApplication.class, args);


	}

}
