package com.latihanBni.batchfile.request;

import java.math.BigDecimal;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvReq {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String namaLengkap;
	private String cif;
	private String nomorRekening;
	private String nomorHp;
	private String email;
	private String tanggalLahir;
	private BigDecimal saldo;
}
