package com.richard.estudos.anotherproject.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.richard.estudos.anotherproject.daos.ReportsDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.Report;
import com.richard.estudos.anotherproject.services.ReportsService;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(MockitoJUnitRunner.class)
public class ReportsServiceTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Mock
	private ReportsDao reportsDao;

	@InjectMocks
	private ReportsService reportsService;
	
	@Test
	public void save_report() {
		Report report = new Report();
		when(reportsDao.save(eq(report))).thenReturn(report);
		
		reportsService.save(report);
		
		verify(reportsDao, times(1)).save(eq(report));
	}
	
	@Test
	public void find_report_by_id() {
		Report returnedReport = ModelUtils.report();
		when(reportsDao.findByExternalIdAndRequester(returnedReport.getExternalId(), returnedReport.getRequester())).thenReturn(returnedReport);
		
		Report report = reportsService.findByExternalIdAndRequester(returnedReport.getExternalId(), returnedReport.getRequester());
		
		assertThat(report.getId()).isEqualTo(1L);
		assertThat(report.getExternalId()).isEqualTo(returnedReport.getExternalId());
		assertThat(report.getRequester()).isEqualTo("Richard");
		assertThat(report.getContent()).isEqualTo("[]");
	}
	
	@Test
	public void find_report_by_invalid_id() {
		UUID externalId = UUID.randomUUID();
		when(reportsDao.findByExternalIdAndRequester(externalId, "Richard")).thenReturn(null);
		expected.expect(NotFoundErrorException.class);
		
		reportsService.findByExternalIdAndRequester(externalId, "Richard");
	}
}
