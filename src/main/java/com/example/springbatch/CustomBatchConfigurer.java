package com.example.springbatch;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomBatchConfigurer extends BasicBatchConfigurer {

    private final DataSource dataSource;

    /**
     * Create a new {@link BasicBatchConfigurer} instance.
     *  @param properties                    the batch properties
     * @param dataSource                    the underlying data source
     * @param transactionManagerCustomizers transaction manager customizers (or
 *                                      {@code null})
     * @param dataSource1
     */
    protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers, DataSource dataSource1) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource1;
    }

    @Override
    protected JobRepository createJobRepository() throws Exception {

        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(getTransactionManager());
        factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
//        factoryBean.setTablePrefix("SYSTEM_"); // 테이블을 생성하지 않으면 에러가 난다

        return factoryBean.getObject();
    }
}
