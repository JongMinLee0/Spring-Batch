package com.example.springbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@Component
public class JobRepositoryListener implements JobExecutionListener {

    private final JobRepository jobRepository;

    public JobRepositoryListener(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20210102").toJobParameters();

        JobExecution lastJobExecution = this.jobRepository.getLastJobExecution(jobName, jobParameters);
        if (lastJobExecution != null) {
            for(StepExecution execution : lastJobExecution.getStepExecutions()){
                BatchStatus status = execution.getStatus();
                System.out.println("status : " + status);
                ExitStatus exitStatus = execution.getExitStatus();
                System.out.println("exitStatus : " + exitStatus);
                String stepName = execution.getStepName();
                System.out.println("stepName : " + stepName);
            }
        }
    }
}
