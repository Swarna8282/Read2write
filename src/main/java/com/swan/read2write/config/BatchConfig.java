package com.swan.read2write.config;

import com.swan.read2write.IdText;
import com.swan.read2write.IdTextProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static com.swan.read2write.Constants.TEST_DATA_FILE_NAME;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@Slf4j
public class BatchConfig {
    private final PlatformTransactionManager ptManager;
    private final DataSource dSource;

    public BatchConfig(PlatformTransactionManager ptManager, DataSource dSource) {
        this.ptManager = ptManager;
        this.dSource = dSource;
    }

    @Bean
    public FlatFileItemReader<String> ffiReader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("idItemReader")
                .resource(new ClassPathResource(TEST_DATA_FILE_NAME))
                .delimited()
                .names("id")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<String>(){{
                    setTargetType(String.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<IdText> jbiWriter() {
        return new JdbcBatchItemWriterBuilder<IdText>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<IdText>())
                .sql("INSERT INTO ID_TEXT (id, text) VALUES (:id, :text)")
                .dataSource(dSource)
                .build();
    }

    @Bean
    public ItemProcessor<String, IdText> iProcessor() {
        return new IdTextProcessor();
    }
    @Bean
    public Step idTextLoad(JobRepository jobRepository, PlatformTransactionManager ptManager) {
        return new StepBuilder("idTextLoad", jobRepository)
                .<String, IdText>chunk(10, ptManager)
                .reader(ffiReader())
                .writer(jbiWriter())
                .processor(iProcessor())
                .build();
    }

    @Bean
    public Job idTextJob(JobRepository jobRepository) {
        return new JobBuilder("idTextJob", jobRepository)
                .start(idTextLoad(jobRepository, ptManager))
                .build();
    }
}
