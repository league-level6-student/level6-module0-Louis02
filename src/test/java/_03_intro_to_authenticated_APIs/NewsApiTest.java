package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;

import _01_intro_to_APIs.data_transfer_objects.Result;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsApiTest {

	NewsApi newsApi;

	@Mock
	WebClient webClientMock;

	@Mock
	RequestHeadersSpec requestHeadersSpecMock;

	@Mock
	RequestHeadersUriSpec requestHeadersUriSpecMock;

	@Mock
	ResponseSpec responseSpecMock;

	@Mock
	Mono<ApiExampleWrapper> apiExampleWrapperMonoBlock;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		newsApi = new NewsApi();
		newsApi.setWebClient(webClientMock);
	}

	@Test
	void itShouldGetNewsStoryByTopic() {
		// given
		String topic = "Cows";
		Article expectedArticle = new Article();
		expectedArticle.setTitle("a");
		expectedArticle.setContent("b");
		expectedArticle.setUrl("www.couwtruth.com");
		List<Article> expectedArticles = Collections.singletonList(expectedArticle);
		ApiExampleWrapper expectedApiExampleWrapper = new ApiExampleWrapper();
		expectedApiExampleWrapper.setArticles(expectedArticles);
		
		// when
		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(apiExampleWrapperMonoBlock);
		when(apiExampleWrapperMonoBlock.block()).thenReturn(expectedApiExampleWrapper);

		// then
		ApiExampleWrapper actualApiExampleWrapper = newsApi.getNewsStoryByTopic(topic);
		verify(webClientMock, times(1)).get();
		Article actualArticle = actualApiExampleWrapper.getArticles().get(0);
		assertEquals(expectedArticle, actualArticle);
	}

	@Test
	void itShouldFindStory() {
		// given
		String topic = "Cows";
		Article expectedArticle = new Article();
		expectedArticle.setTitle("a");
		expectedArticle.setContent("b");
		expectedArticle.setUrl("www.couwtruth.com");
		List<Article> expectedArticles = Collections.singletonList(expectedArticle);
		
		ApiExampleWrapper expectedApiExampleWrapper = new ApiExampleWrapper();
		expectedApiExampleWrapper.setArticles(expectedArticles);
		// when
		when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any())).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(apiExampleWrapperMonoBlock);
		when(apiExampleWrapperMonoBlock.block()).thenReturn(expectedApiExampleWrapper);

		// then
		String expectedStory = "a" + " -\n"+"b"+"\nFull article: " + "www.couwtruth.com";
		String actualStory = newsApi.findStory(topic);
		verify(webClientMock, times(1)).get();
		assertEquals(expectedStory, actualStory);
			
	}

}