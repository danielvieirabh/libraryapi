package io.github.cursodanzin.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url; //nao precisa do private
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver}")
    String driver;

 //   @Bean
   public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
   }
    //Recomendado a usar esse , o Spring Boot ja utiliza por PADRÃO
   // @Bean
    public DataSource hikariDataSource() {
       HikariConfig config = new HikariConfig();
       config.setJdbcUrl(url);
       config.setUsername(username);
       config.setPassword(password);
       config.setDriverClassName(driver);

       //Vai de 1 a 10 no maximo :
       config.setMaximumPoolSize(10); //Libera 10 conexoes no maximo
       config.setMinimumIdle(1); //Libera 1 conexoes no minimo
       config.setPoolName("library-db-pool");
       config.setMaxLifetime(600000);//600 mil milisegundos (Dura 10 minutos, depois ele vai morrer)
       config.setConnectionTimeout(100000); //Tempo que vai gastar para ter uma conexao, se passar vai dar erro
       config.setConnectionTestQuery("select 1"); //query de teste se vai connectar

        return new HikariDataSource(config);
   }
}
