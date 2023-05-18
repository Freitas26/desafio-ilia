package com.example.ilia.service;

import com.example.ilia.controller.dto.RegistroDTO;
import com.example.ilia.controller.dto.Relatorio;
import com.example.ilia.controller.exceptions.BusinessException;
import com.example.ilia.controller.exceptions.ConflictException;
import com.example.ilia.controller.exceptions.InputException;
import com.example.ilia.entity.Registro;
import com.example.ilia.repository.RegistroRepository;
import com.example.ilia.serviceImpl.PontoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class PontoServiceTest {

	@Mock
	private RegistroRepository registroRepository;

	@InjectMocks
	private PontoService pontoService = new PontoServiceImpl();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testInsereBatida_WithValidInput_ShouldReturnRegistro() throws Exception {
		// Arrange
		String momento = "2023-05-17T08:00:00";
		Registro existingRegistro = new Registro("2023-05-17");
		existingRegistro.setHorarios(new String[4]);
		when(registroRepository.findByDia("2023-05-17")).thenReturn(Optional.of(existingRegistro));
		when(registroRepository.save(any(Registro.class))).thenReturn(existingRegistro);

		// Act
		RegistroDTO result = pontoService.insereBatida(momento);

		// Assert
		assertNotNull(result);
		assertArrayEquals(new String[]{"08:00:00", null, null, null}, result.getHorarios());
	}

	@Test
	public void testInsereBatida_WithNullMomento_ShouldThrowInputException() {

		String momento = null;
		assertThrows(InputException.class, () -> pontoService.insereBatida(momento));
	}
	@Test
	public void testInsereBatida_WithWrongMomento_ShouldThrowInputException() {

		String momento = "2023-05-08:00:00";
		assertThrows(InputException.class, () -> pontoService.insereBatida(momento));
	}

	@Test
	public void testInsereBatida_WithWeekendDay_ShouldThrowBusinessException() throws Exception {
		String momento = "2023-05-20T08:00:00";
		assertThrows(BusinessException.class, () -> pontoService.insereBatida(momento));
	}

	@Test
	public void testInsereBatida_WithExceededHorariosPerDay_ShouldThrowBusinessException() throws Exception {
		String momento = "2023-05-17T16:00:00";
		Registro existingRegistro = new Registro("2023-05-17");
		existingRegistro.setHorarios(new String[]{"08:00:00", "12:00:00", "13:00:00", "18:00:00"});
		when(registroRepository.findByDia("2023-05-17")).thenReturn(Optional.of(existingRegistro));
		assertThrows(BusinessException.class, () -> pontoService.insereBatida(momento));
	}

	@Test
	public void testInsereBatida_WithDuplicateHorario_ShouldThrowConflictException() throws Exception {
		String momento = "2023-05-17T08:00:00";
		Registro existingRegistro = new Registro("2023-05-17");
		existingRegistro.setHorarios(new String[]{"08:00:00", null, null, null});
		when(registroRepository.findByDia("2023-05-17")).thenReturn(Optional.of(existingRegistro));
		assertThrows(ConflictException.class, () -> pontoService.insereBatida(momento));
	}

	@Test
	public void testGerarRelatorioMensal_WithValidMes_ShouldReturnRelatorio() {
		String mes = "2023-05";
		List<Registro> registros = new ArrayList<>();
		registros.add(new Registro("2023-05-01"));
		registros.add(new Registro("2023-05-02"));
		when(registroRepository.findByDiaStartsWith("2023-05")).thenReturn(Optional.of(registros));
		Relatorio result = pontoService.gerarRelatorioMensal(mes);

		assertNotNull(result);
		assertEquals("2023-05", result.getMes());
		assertEquals(registros.size(), result.getRegistros().size());
	}

	@Test
	public void testGerarRelatorioMensal_WithInvalidMes_ShouldThrowException() {
		String mes = "2023-13";

		assertThrows(Exception.class, () -> pontoService.gerarRelatorioMensal(mes));
	}
}