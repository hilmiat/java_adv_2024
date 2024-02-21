package id.my.hilmiat.konfigurasi;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;

import com.mysql.cj.jdbc.MysqlDataSource;

import id.my.hilmiat.entity.Person;
import id.my.hilmiat.mapper.PersonMapper;

public class MyBatisConfig {
    // method configuration
    public static Configuration konfigure() {
        // set connection
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("service_demo");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser("service_user");
        dataSource.setPassword("service_password");

        TransactionFactory transaction = new JdbcTransactionFactory();
        Environment env = new Environment("dev", transaction, dataSource);
        Configuration config = new Configuration(env);

        TypeAliasRegistry alias = config.getTypeAliasRegistry();
        alias.registerAlias("Person", Person.class);

        config.addMapper(PersonMapper.class);
        return config;
    }
}
