package com.latihanBni.batchfile.entity;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String namaLengkap;
	private String cif;
	private String nomorRekening;
	private String nomorHp;
	private String email;
	private Date tanggalLahir;
	private BigDecimal saldo;
	private BigDecimal saldoPengembangan;
}