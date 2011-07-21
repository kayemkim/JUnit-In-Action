package km.junit.ch8;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import km.junit.ch8.SampleServlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import junit.framework.TestCase;

public class TestSampleServletTest {
	
	private SampleServlet servlet;
	@Mock private HttpServletRequest mockedRequest;
	@Mock private HttpSession mockedSession;
	
	@Before
	public void setUp() {
		servlet = new SampleServlet();
		mockedRequest = mock(HttpServletRequest.class);
		mockedSession = mock(HttpSession.class);
	}
	
	// 인증 성공
	@Test
	public void testIsAuthenticatedOk() {
		// Mock 행동 정의
		when(mockedSession.getAttribute(eq("authenticated")))
		.thenReturn("true");
		when(mockedRequest.getSession(eq(false)))
		.thenReturn(mockedSession);
		
		// EashMock의 replay가 없어도 됨
		
		assertTrue("인증 결과가 true여야 ", servlet.isAuthenticated(mockedRequest));
		
		verify(mockedSession).getAttribute("authenticated");
	}
	
	// 인증 실패
	@Test
	public void testIsAuthenticatedNotOk() {
		when(mockedSession.getAttribute(eq("authenticated")))
		.thenReturn("false");
		when(mockedRequest.getSession(eq(false)))
		.thenReturn(mockedSession);
		
		assertFalse("인증 결과가 false여야 함", servlet.isAuthenticated(mockedRequest));
		
		verify(mockedSession).getAttribute("authenticated");
	}
	
	// 인증 실패 (no session)
	@Test
	public void testIsAuthenticatedNoSession() {
		when(mockedRequest.getSession(eq(false)))
		.thenReturn(null);
		
		assertFalse("인증 결과가 false여야 함", servlet.isAuthenticated(mockedRequest));
	}
	
	@After
	public void tearDown() {
		verify(mockedRequest).getSession(false);
		/*
		 * no session 테스트에서는 session관련된 검증을 안함..
		 * 주석 제거시 테스트 실패 (wanted, but not invoked)
		 */
		verify(mockedSession).getAttribute("authenticated");
		
		// easyMock은 verify(mock) 으로 끝나는데..
	}

}
