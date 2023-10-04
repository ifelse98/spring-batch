package com.latihanBni.batchfile.config;

import com.latihanBni.batchfile.request.CsvReq;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
@Configuration
public class CsvReader {

    public FlatFileItemReader<CsvReq> reader() {
        FlatFileItemReader<CsvReq> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/dataSeederMini.csv"));
//        itemReader.setResource(new FileSystemResource("app/dataSeederMini.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    public LineMapper<CsvReq> lineMapper() {
        DefaultLineMapper<CsvReq> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("Nama Lengkap", "CIF", "Nomor Rekening", "Nomor HP", "Email", "Tanggal Lahir", "Saldo");

        BeanWrapperFieldSetMapper<CsvReq> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CsvReq.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
