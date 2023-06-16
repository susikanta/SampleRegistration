package com.sampleregistration.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sampleregistration.dto.request.UrlObject;
import com.sampleregistration.dto.response.DeleteResponse;
import com.sampleregistration.dto.response.ResponseObject;
import com.sampleregistration.exception.UploadException;
import com.sampleregistration.util.ImageUtil;

@Service
public class DeleteImageService {
	private static final Logger LOGGER = LogManager.getLogger(ImageUploadService.class);
	public static final String CLIENT_ID = "d93c4f260e94fef";
	public static final String IMGUR_URL = "https://api.imgur.com/3/image";

	public DeleteResponse processDeleteImage(String id) throws UploadException {
		ResponseObject responseObject = deleteImage(id);
		//update DB
		return DeleteResponse.builder()
				.success(responseObject.isSuccess())
				.statusCode(responseObject.getStatusCode())
				.build();
	}

	private ResponseObject deleteImage(String id) throws UploadException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpPostRequest = new HttpDelete(IMGUR_URL + "/" + id);
		httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);

		CustomResponseHandler customResponseHandler = new CustomResponseHandler();
		int status = -1;
		ResponseObject responseBody;
		try {
			responseBody = (ResponseObject) httpClient.execute(httpPostRequest, customResponseHandler);
			LOGGER.info("imgur response "+ responseBody);
			status = responseBody.getStatusCode();
			if (status >= 200 && status < 300) {
				LOGGER.info("Image deleted at imgur");
			} else {
				LOGGER.info("Failed to delete at imgur");
			}
			httpClient.close();
		} catch (UnsupportedEncodingException e) {
			throw new UploadException(e, status);
		} catch (ClientProtocolException e) {
			throw new UploadException(e, status);
		} catch (IOException e) {
			throw new UploadException(e, status);
		}
		return responseBody;
	}
}
