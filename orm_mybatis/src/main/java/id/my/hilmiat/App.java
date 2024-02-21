package id.my.hilmiat;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;

import com.mysql.cj.jdbc.MysqlDataSource;

import id.entity.Person;
import id.my.hilmiat.mapper.PersonMapper;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Configuration conf = konfigure();
        SqlSessionFactory session = new SqlSessionFactoryBuilder().build(conf);
        SqlSession sqlSession = session.openSession();
        PersonMapper pm = sqlSession.getMapper(PersonMapper.class);

        // pm.addData(new Person(5L, "Person", "Baru"));
        // sqlSession.commit();

        // List<Person> dataPerson = pm.getAll();
        // for (Person p : dataPerson) {
        //     System.out.println("Person: " + p.getFirstname());
        // }
        // System.out.println("Test get by id");
        // Person personcari = pm.get(3L);
        // System.out.println(personcari.getFirstname()+" "+personcari.getLastname());

        // System.out.println("Test update data");
        // personcari.setFirstname("nama setelah update");
        // pm.update(personcari);
        // sqlSession.commit();

        // System.out.println("Test get by id");
        // personcari = pm.get(3L);
        // System.out.println(personcari.getFirstname()+" "+personcari.getLastname());

        pm.delete(3L);
        sqlSession.commit();
    
    }

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
