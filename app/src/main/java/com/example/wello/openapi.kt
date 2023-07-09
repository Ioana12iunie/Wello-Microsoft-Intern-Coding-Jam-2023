import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

fun main() {

    // TODO: replace these with the values from the sliders
    val sleepAmount = "8 hours"
    val exerciseAmount = "30 minutes"
    val meditateAmount = "15 minutes"

    val prompt = "I want to sleep $sleepAmount, exercise for $exerciseAmount, and meditate for $meditateAmount. Now, Provide me with a plan to do so. "

    val maxTokens = 50
    val apiKey = "<your-api-key>"
    val apiUrl = "https://api.openai.com/v1/engines/davinci-codex/completions"

    val client = OkHttpClient()
    val mediaType = "application/json; charset=utf-8".toMediaType()

    val requestBody = """
        {
            "prompt": "$prompt",
            "max_tokens": $maxTokens
        }
    """.trimIndent()

    val request = Request.Builder()
        .url(apiUrl)
        .addHeader("Authorization", "Bearer $apiKey")
        .post(requestBody.toRequestBody(mediaType))
        .build()

    val response = client.newCall(request).execute()
    val responseBody = response.body?.string()

    if (response.isSuccessful && responseBody != null) {
        val completion = responseBody.split("\"text\":\"")[1].split("\"")[0]
        println("Generated completion: $completion")
    } else {
        println("Failed to generate completion. Error: ${response.code} - ${response.message}")
    }
}
