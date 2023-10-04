package com.latihanBni.batchfile.config;

import lombok.AllArgsConstructor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.latihanBni.batchfile.entity.CsvFile;
//import com.latihanBni.batchfile.repository.CsvRepository;
import com.latihanBni.batchfile.request.CsvReq;

@Configuration
@AllArgsConstructor
public class BatchConfig {

    private CsvReader csvReader;

    private CsvWriter csvWriter;

    @Bean
    public FlatFileItemReader<CsvReq> reader() {
        return csvReader.reader();
    }

    @Bean
    public CsvProcessor processor() {
        return new CsvProcessor();
    }

//	@Bean
//	public RepositoryItemWriter<CsvFile> writer() {
//		RepositoryItemWriter<CsvFile> writer = new RepositoryItemWriter<>();
//		writer.setRepository(csvRepository);
//		writer.setMethodName("save");
//		return writer;
//	}

    @Bean
    public JdbcBatchItemWriter<CsvFile> jdbcWriter() {
        return csvWriter.jdbcWriter();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("csv-step", jobRepository)
                .<CsvReq, CsvFile>chunk(1000, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(jdbcWriter())
                .faultTolerant()
                .skipLimit(10)
                .skip(Exception.class)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("importCsv", jobRepository)
                .flow(step1(jobRepository, transactionManager))
                .end()
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(16);
        taskExecutor.setThreadNamePrefix("batch-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }

}