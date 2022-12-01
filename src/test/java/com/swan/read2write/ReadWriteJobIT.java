package com.swan.read2write;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static com.swan.read2write.Constants.RECORDS_COUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReadWriteJobIT {
    @Autowired
    private JobLauncher jLauncher;
    @Autowired
    ReadWriteService rwService;
    @Autowired
    @Qualifier(value = "idTextJob")
    private Job idTextJob;

    @Test
    public void idTextJob_test() throws
            JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {

        JobExecution jExecution = jLauncher.run(idTextJob, new JobParameters());

        assertEquals(ExitStatus.COMPLETED, jExecution.getExitStatus());
        assertEquals(RECORDS_COUNT, rwService.getAllTexts().size());
    }
}
