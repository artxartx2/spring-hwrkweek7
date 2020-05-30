package pl.kurs.springhwrkweek7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS cars (car_id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "mark VARCHAR(25) NOT NULL, model VARCHAR(25) NOT NULL, color VARCHAR(25) NOT NULL, " +
                "production_year INTEGER NOT NULL)";
        getJdbcTemplate().update(sql);

        sql = "CREATE TABLE IF NOT EXISTS news (news_id BIGINT PRIMARY KEY AUTO_INCREMENT ," +
                " title varchar(255), description varchar(5000), author varchar(255) ,news_date varchar(30) )";
        getJdbcTemplate().update(sql);

    }

}
