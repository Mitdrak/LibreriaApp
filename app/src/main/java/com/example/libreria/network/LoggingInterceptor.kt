import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body
        val buffer = Buffer()
        requestBody?.writeTo(buffer)
        println("Request URL: ${request.url}")
        println("Request Body: ${buffer.readUtf8()}")
        return chain.proceed(request)
    }
}