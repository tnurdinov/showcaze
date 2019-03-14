package com.tnurdinov.showcaze

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tnurdinov.showcaze.data.ContentScreenState
import com.tnurdinov.showcaze.data.ImageService
import com.tnurdinov.showcaze.data.ListScreenState
import com.tnurdinov.showcaze.repositories.ImageRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImageRepositoryUnitTest {
    lateinit var blogRepository : ImageRepository
    lateinit var mockServer : MockWebServer
    lateinit var blogService : ImageService

    @Before
    @Throws fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()


        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(mockServer.url("/"))
            .build()

        blogService = retrofit.create(ImageService::class.java)
        blogRepository = ImageRepository(blogService)
    }


    @After
    @Throws fun tearDown() {
        mockServer.shutdown()
    }


    @Test fun `should return widget image`() {
        val path = "/content"

        // Mock a response with status 200 and sample JSON output
        val mockReponse = MockResponse()
            .setResponseCode(200)
            .setBody("{\n" +
                    "  \"content\": [\n" +
                    "    {\n" +
                    "      \"type\": \"image_widget\",\n" +
                    "      \"cols\": 1,\n" +
                    "      \"images\": [\n" +
                    "        {\n" +
                    "          \"url\": \"https://images.unsplash.com/photo-1439158771502-46975f6e44e9?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjU4NjY4fQ\",\n" +
                    "          \"width\": 1080,\n" +
                    "          \"format\": \"jpg\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }]}")
        // Enqueue request
        mockServer.enqueue(mockReponse)

        val screenState = runBlocking {
            blogRepository.getContent()
        }

        val content = (screenState as ContentScreenState.Data)

        assertEquals(content.contentList.first().type, "image_widget")

        val request = mockServer.takeRequest()

        assertEquals(path, request.path)
    }

    @Test
    fun `should return slider widget`() {
        val path = "/content"
        val mockReponse = MockResponse()
            .setResponseCode(200)
            .setBody("{\n" +
                    "  \"content\": [\n" +
                    "    {\n" +
                    "      \"type\": \"slider_widget\",\n" +
                    "      \"show\": 1,\n" +
                    "      \"images\": [\n" +
                    "        {\n" +
                    "          \"url\": \"https://images.unsplash.com/photo-1542491770-8e718e5a0f57?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjU4NjY4fQ\",\n" +
                    "          \"width\": 1080,\n" +
                    "          \"format\": \"jpg\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }]}")

        mockServer.enqueue(mockReponse)

        val screenState = runBlocking {
            blogRepository.getContent()
        }

        val content = (screenState as ContentScreenState.Data)

        assertEquals(content.contentList.first().type, "slider_widget")

        val request = mockServer.takeRequest()

        assertEquals(path, request.path)
    }

    @Test
    fun `should return carousel widget`() {
        val path = "/content"
        val mockReponse = MockResponse()
            .setResponseCode(200)
            .setBody("{\n" +
                    "  \"content\": [\n" +
                    "    {\n" +
                    "      \"type\": \"carousel_widget\",\n" +
                    "      \"title\": \"Editors choice\",\n" +
                    "      \"url\": \"https://demo4192437.mockable.io/list\",\n" +
                    "      \"show\": 3\n" +
                    "    }]}")

        mockServer.enqueue(mockReponse)

        val screenState = runBlocking {
            blogRepository.getContent()
        }

        val content = (screenState as ContentScreenState.Data)

        assertEquals(content.contentList.first().type, "carousel_widget")

        val request = mockServer.takeRequest()

        assertEquals(path, request.path)
    }

    @Test
    fun `should return list of images`() {
        val path = "/list"
        val mockReponse = MockResponse()
            .setResponseCode(200)
            .setBody("{\n" +
                    "  \"images\": [\n" +
                    "        {\n" +
                    "          \"url\": \"https://images.unsplash.com/photo-1545295771-85a80dffc539?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjU4NjY4fQ\",\n" +
                    "          \"width\": 400,\n" +
                    "          \"format\": \"jpg\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"url\": \"https://images.unsplash.com/photo-1543587545-bc0cedb3cf38?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjU4NjY4fQ\",\n" +
                    "          \"width\": 400,\n" +
                    "          \"format\": \"jpg\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"url\": \"https://images.unsplash.com/photo-1548871269-1b7b24708c4e?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjU4NjY4fQ\",\n" +
                    "          \"width\": 400,\n" +
                    "          \"format\": \"jpg\"\n" +
                    "        } ]\n" +
                    "}")

        mockServer.enqueue(mockReponse)

        val screenState = runBlocking {
            blogRepository.getImageList()
        }

        val content = (screenState as ListScreenState.Data)

        assertEquals(content.imageList.first().width, 400)

        val request = mockServer.takeRequest()

        assertEquals(path, request.path)
    }
}