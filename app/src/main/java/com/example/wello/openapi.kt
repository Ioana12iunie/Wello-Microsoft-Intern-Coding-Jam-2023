import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

fun main() {
    val sleepAmount = retrieveSleepAmount()
    val exerciseAmount = retrieveExerciseAmount()
    val meditateAmount = retrieveMeditateAmount()

    val prompt = "Write a schedule for my day that must meet the following requirements. \n" +
            "(1) Includes $sleepAmount contiguous hours of sleep. \n" +
            "(2) Includes a contiguous $exerciseAmount of exercise. \n" +
            "(3) Includes $meditateAmount of mindful time.\n" +
            "(4) Includes time for 2-3 activities/tasks. \n" +
            "(5) Includes 5 to 10 minute transition breaks between activities. \n" +
            "The format for the schedule should look like: start time - end time: activity name"

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
        return completion
    } else {
        println("Failed to generate completion. Error: ${response.code} - ${response.message}")
    }
}

private fun retrieveSleepAmount(): String {
    val binding: FragmentPlannerBinding = requireBinding()
    return binding.sliderSleep.value.toInt().toString()
}



private fun retrieveExerciseAmount(): String {
    val binding: FragmentPlannerBinding = requireBinding()
    return binding.sliderExercise.value.toInt().toString()
}



private fun retrieveMeditateAmount(): String {
    val binding: FragmentPlannerBinding = requireBinding()
    return binding.sliderMeditate.value.toInt().toString()
}
