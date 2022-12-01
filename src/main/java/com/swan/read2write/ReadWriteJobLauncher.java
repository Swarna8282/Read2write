//package com.swan.read2write;
//
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ReadWriteJobLauncher {
//    private final JobRepository jRepo;
//
//    public ReadWriteJobLauncher(JobRepository jRepo) {
//        this.jRepo = jRepo;
//    }
//
//    @Bean
//    public JobLauncher jobLauncher() throws Exception {
//        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
//        jobLauncher.setJobRepository(jRepo);
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }
//}
