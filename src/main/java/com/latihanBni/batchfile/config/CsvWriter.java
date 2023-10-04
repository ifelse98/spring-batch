package com.latihanBni.batchfile.config;

import com.latihanBni.batchfile.entity.CsvFile;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
@AllArgsConstructor
public class CsvWriter {

    private DataSource dataSource;

    public JdbcBatchItemWriter<CsvFile> jdbcWriter() {
        return new JdbcBatchItemWriterBuilder<CsvFile>()
                .dataSource(dataSource)
                .sql("INSERT INTO C##NIRWAN.CSV_TABLE (NAMA_LENGKAP, CIF, NOMOR_REKENING, NOMOR_HP, EMAIL, TANGGAL_LAHIR, SALDO, SALDO_PENGEMBANGAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter((item, ps) -> {
                    ps.setString(1, item.getNamaLengkap());
                    ps.setString(2, item.getCif());
                    ps.setString(3, item.getNomorRekening());
                    ps.setString(4, item.getNomorHp());
                    ps.setString(5, item.getEmail());
                    ps.setDate(6, item.getTanggalLahir());
                    ps.setBigDecimal(7, item.getSaldo());
                    ps.setBigDecimal(8, item.getSaldoPengembangan());
                })
                .build();
    }
}
