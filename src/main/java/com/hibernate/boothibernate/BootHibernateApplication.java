package com.hibernate.boothibernate;

import com.hibernate.boothibernate.base.SimpleJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lushun
 * @date 2018-12-27
 */


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepositoryImpl.class)
public class BootHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootHibernateApplication.class, args);
    }

}

