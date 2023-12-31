import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.serialization.*
import kotlinx.serialization.json.*

val openAI = OpenAI("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")
val id = ModelId("gpt-4")
val model: Model = openAI.model(id)
/*
* This function creates a request string based on the information passed in
* (from the sliders). We then use this request string in another request function
* to make the actual API call.
* */
fun createRequest(sleepAmount, exerciseAmount, meditateAmount) {
    val text = "Write a schedule for my day that must meet the following requirements. \n" +
            "(1) Includes " + sleepAmount + " contiguous hours of sleep. \n" +
            "(2) Includes a contiguous " + exerciseAmount + " minutes of exercise. \n" +
            "(3) Includes " + meditateAmount + " minutes of mindful time.\n" +
            "(4) Includes time for 2-3 activities/tasks. \n" +
            "(5) Includes 5 to 10 minute transition breaks between activities. \n" +
            "The format for the schedule should look like: start time - end time: activity name"
    val completionRequest = CompletionRequest(
            model = model,
            prompt = text,
            max_tokens = 100,
            temperature = 0.5,
            n = 1
    )
    return completionRequest
}
/*
* This function makes the request to the API when given a request string containing
* all the request parameters. It returns the plan returned by the language model.
* */
fun requestOpenAI(completionRequest) {
    val completion: TextCompletion = openAI.completion(completionRequest)
    val jsonObject: JSONObject = new JSONObject (completion)
    val plan = jsonObject.getString("choices")
/* NOTE: this may not be the entirely correct field of the response to use;
 * we may need to look at the returned JSON and pick out the right field
 */
    return plan
}