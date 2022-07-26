package com.example.springbatch;

import com.example.springbatch.context.ExecutionContextTasklet1;
import com.example.springbatch.context.ExecutionContextTasklet2;
import com.example.springbatch.context.ExecutionContextTasklet3;
import com.example.springbatch.context.ExecutionContextTasklet4;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutionContextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;

    public ExecutionContextConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ExecutionContextTasklet1 executionContextTasklet1, ExecutionContextTasklet2 executionContextTasklet2, ExecutionContextTasklet3 executionContextTasklet3, ExecutionContextTasklet4 executionContextTasklet4) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.executionContextTasklet1 = executionContextTasklet1;
        this.executionContextTasklet2 = executionContextTasklet2;
        this.executionContextTasklet3 = executionContextTasklet3;
        this.executionContextTasklet4 = executionContextTasklet4;
    }

    @Bean
    public Job BatchJob() {
        return this.jobBuilderFactory.get("Job")
                .start(step1())
                .next(step2())
                .next(step3())
                .next(step4())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(executionContextTasklet1)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(executionContextTasklet2)
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(executionContextTasklet3)
                .build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(executionContextTasklet4)
                .build();
    }
}
