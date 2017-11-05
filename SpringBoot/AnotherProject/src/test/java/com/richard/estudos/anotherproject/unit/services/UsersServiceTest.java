package com.richard.estudos.anotherproject.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.richard.estudos.anotherproject.daos.UsersDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.User;
import com.richard.estudos.anotherproject.services.CategoriesService;
import com.richard.estudos.anotherproject.services.UsersService;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Mock
	private UsersDao usersDao;
	
	@Mock
	private CategoriesService categoriesService;
	
	@InjectMocks
	private UsersService usersService;
	
	@Test
	public void save_user() {
		User user = ModelUtils.user(null, "Some name", "2010-01-24");
		when(usersDao.save(user)).thenReturn(user);
		
		usersService.save(user);
		
		verify(usersDao, times(1)).save(user);
	}
	
	@Test
	public void save_user_with_invalid_category() {
		User user = ModelUtils.user(null, "Some name", "2010-01-24");
		when(categoriesService.findById(1L)).thenThrow(new NotFoundErrorException());
		expected.expect(NotFoundErrorException.class);
		
		usersService.save(user);
		
		verify(usersDao, times(0)).save(user);
	}
	
	@Test
	public void find_all_users() {
		when(usersDao.findAll()).thenReturn(new ArrayList<User>());
		
		Iterable<User> users = usersService.findAll();
		
		assertThat(users).isEmpty();
		verify(usersDao, times(1)).findAll();
	}
}
