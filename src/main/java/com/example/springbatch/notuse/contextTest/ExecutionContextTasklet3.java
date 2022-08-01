package com.example.springbatch.notuse.contextTest;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step3 was executed");
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");

        // 재 실행시 ExecutionContext에 name=user1이 있기 때문에 예외를 발생시키지 않는다
        if(name == null){
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
            throw new RuntimeException("step3 was failed");
        }
        return RepeatStatus.FINISHED;
    }
}
