/***
 *   Copyleft 2014 - WareNinja.com / Rumble In The Jungle!
 * 
 *  @author: yg@wareninja.com
 *  @see https://github.com/WareNinja
 *  disclaimer: I code for fun, dunno what I'm coding about :-)
 */

package com.wareninja.opensource.discourse;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.TextUtils;

import com.wareninja.opensource.discourse.utils.ResponseListener;
import com.wareninja.opensource.discourse.utils.MyWebClient;
import com.wareninja.opensource.discourse.utils.ResponseMeta;
import com.wareninja.opensource.discourse.utils.ResponseModel;

public class DiscourseApiClient {

	String api_url = "";// base url. e.g. http://your_discourse_domain.com
	String api_key = "";
	String api_username = "";
	public DiscourseApiClient(String api_url, String api_key, String api_username) {
		this.api_url = api_url;
		this.api_key = api_key;
		this.api_username = api_username;
	}
	//public DiscourseApiClient() {
	//}
	public void setApiBase(String api_url, String api_key, String api_username) {
		this.api_url = api_url;
		this.api_key = api_key;
		this.api_username = api_username;
	}
	public static enum FILTER {
		LIKE,
		WAS_LIKED,
		BOOKMARK,
		NEW_TOPIC,
		REPLY,
		RESPONSE,
		MENTION,
		QUOTE,
		STAR,
		EDIT,
		NEW_PRIVATE_MESSAGE,
		GOT_PRIVATE_MESSAGE
	};
	
/////////////////////
//USERS
/////////////////////

	public void getUser(Map<String, String> parameters, ResponseListener responseListener) {
		
		// example: https://base_domain/users/<username>.json?api_key=<key>&api_username=<caller_username>
		
		MyWebClient webClient = new MyWebClient(this.api_url);
		if (parameters==null) parameters = new HashMap<String, String>();
		if (!TextUtils.isEmpty(this.api_key)) parameters.put("api_key", this.api_key);
		if (!TextUtils.isEmpty(this.api_username)) parameters.put("api_username", this.api_username);
		
		String methodName = "";
		methodName += "/users/" + this.api_username + ".json";
		
		responseListener.onBegin("BEGIN"+"|"+"getUser"+"| methodName:"+methodName );
		
		String responseStr = webClient.get(methodName, parameters);
		ResponseModel responseModel = new ResponseModel();
		responseModel.meta.code = webClient.getHttpResponseCode();
		responseModel.data = responseStr;
		if (responseModel.meta.code<=201) { // success
			responseListener.onComplete_wModel(responseModel);
		}
		else {// error occured!
			responseModel.meta.errorType = "general";
			responseModel.meta.errorDetail = responseStr;
			responseListener.onError_wMeta(responseModel.meta);
		}
	}
	public void createUser(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void approveUser(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void activateUser(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void deleteUser(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void loginUser(Map<String, String> parameters, ResponseListener responseListener) {
		
		MyWebClient webClient = new MyWebClient(this.api_url);
		if (parameters==null) parameters = new HashMap<String, String>();
		if (!TextUtils.isEmpty(this.api_key)) parameters.put("api_key", this.api_key);
		if (!TextUtils.isEmpty(this.api_username)) parameters.put("api_username", this.api_username);
		
		String methodName = "";
		methodName += "/session";
		
		responseListener.onBegin("BEGIN"+"|"+"loginUser"+"| methodName:"+methodName );
		
		String responseStr = webClient.post(methodName, parameters);
		ResponseModel responseModel = new ResponseModel();
		responseModel.meta.code = webClient.getHttpResponseCode();
		responseModel.data = responseStr;
		if (responseModel.meta.code<=201) { // success
			responseListener.onComplete_wModel(responseModel);
		}
		else {// error occured!
			responseModel.meta.errorType = "general";
			responseModel.meta.errorDetail = responseStr;
			responseListener.onError_wMeta(responseModel.meta);
		}
	}
	public void logoutUser(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void fetchConfirmationValue(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	
///////////////////////
//TOPICS AND REPLIES
///////////////////////
	
	public void createTopic(Map<String, String> parameters, ResponseListener responseListener) {
		
		MyWebClient webClient = new MyWebClient(this.api_url);
		if (parameters==null) parameters = new HashMap<String, String>();
		//if (!TextUtils.isEmpty(this.api_key)) parameters.put("api_key", this.api_key);
		//if (!TextUtils.isEmpty(this.api_username)) parameters.put("api_username", this.api_username);
		
		String methodName = "";
		methodName += "/posts";
		methodName = webClient.enrichMethodName(methodName, this.api_key, this.api_username);// append api_key and api_username
		
		responseListener.onBegin("BEGIN"+"|"+"createTopic"+"| methodName:"+methodName );
		
		String responseStr = webClient.post(methodName, parameters);
		ResponseModel responseModel = new ResponseModel();
		responseModel.meta.code = webClient.getHttpResponseCode();
		responseModel.data = responseStr;
		if (responseModel.meta.code<=201) { // success
			responseListener.onComplete_wModel(responseModel);
		}
		else {// error occured!
			responseModel.meta.errorType = "general";
			responseModel.meta.errorDetail = responseStr;
			responseListener.onError_wMeta(responseModel.meta);
		}
	}
	public void getCreatedTopics(Map<String, String> parameters, ResponseListener responseListener) {
		MyWebClient webClient = new MyWebClient(this.api_url);
		if (parameters==null) parameters = new HashMap<String, String>();
		//if (!TextUtils.isEmpty(this.api_key)) parameters.put("api_key", this.api_key);
		//if (!TextUtils.isEmpty(this.api_username)) parameters.put("api_username", this.api_username);
		
		String methodName = "";
		methodName += "/user_actions.json";
		methodName = webClient.enrichMethodName(methodName, this.api_key, this.api_username);// append api_key and api_username
		
		responseListener.onBegin("BEGIN"+"|"+"getCreatedTopics"+"| methodName:"+methodName );
		if (!TextUtils.isEmpty(this.api_username)) parameters.put("username", this.api_username);
		//parameters.put("filter", FILTER.NEW_TOPIC.name());
		String responseStr = webClient.get(methodName, parameters);
		ResponseModel responseModel = new ResponseModel();
		responseModel.meta.code = webClient.getHttpResponseCode();
		responseModel.data = responseStr;
		if (responseModel.meta.code<=201) { // success
			responseListener.onComplete_wModel(responseModel);
		}
		else {// error occured!
			responseModel.meta.errorType = "general";
			responseModel.meta.errorDetail = responseStr;
			responseListener.onError_wMeta(responseModel.meta);
		}
	}
	public void replyToTopic(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void replyToPost(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void getTopicAndReplies(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void deleteTopic(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void updateTopic(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void updatePost(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	
/////////////////////
//PRIVATE MESSAGES
/////////////////////
	public void createPrivateMessage(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void getPrivateMessages(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void getPrivateMessageThread(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void getSentPrivateMessages(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void getReceivedPrivateMessages(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void replyToPrivateMessage(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	
/////////////////////
//SEARCH
/////////////////////
	public void searchForUser(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	public void search(Map<String, String> parameters, ResponseListener responseListener) {
		// TODO: 
	}
	
}