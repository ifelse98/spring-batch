package com.latihanBni.batchfile.config;

import com.latihanBni.batchfile.entity.CsvFile;
import com.latihanBni.batchfile.request.CsvReq;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CsvProcessor implements ItemProcessor<CsvReq, CsvFile> {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private BigDecimal pengembanganRate = new BigDecimal("0.005");

    @Override
    public CsvFile process(CsvReq item) throws Exception {
        try {
            System.out.println("Tanggal sebelum diubah : " + item.getTanggalLahir());

            CsvFile csvFile = new CsvFile();
            csvFile.setNamaLengkap(item.getNamaLengkap());
            csvFile.setCif(item.getCif());
            csvFile.setNomorRekening(item.getNomorRekening());
            csvFile.setNomorHp(item.getNomorHp());
            csvFile.setEmail(item.getEmail());

            String dateString = item.getTanggalLahir();
            LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
            Date date = Date.valueOf(localDate);
            csvFile.setTanggalLahir(date);
            System.out.println("Tanggal sesudah diubah : " + date);

            csvFile.setSaldo(item.getSaldo());
            System.out.println("Saldo : " + item.getSaldo());

            csvFile.setSaldoPengembangan(item.getSaldo().add(item.getSaldo().multiply(pengembanganRate)));
            System.out.println("Saldo Pengembangan : " + csvFile.getSaldoPengembangan());

            return csvFile;
        } catch (Exception e) {
            System.err.println("Error dalam proses: " + e.getMessage());
            System.err.println("Nama dan Tanggal: " + item.getNamaLengkap() + " " + item.getTanggalLahir());
            throw e;
        }
    }

}
