package com.sampleregistration.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sampleregistration.dto.request.UrlObject;
import com.sampleregistration.dto.response.ImageResponse;
import com.sampleregistration.dto.response.ResponseObject;
import com.sampleregistration.dto.response.Status;
import com.sampleregistration.exception.UploadException;
import com.sampleregistration.util.ImageUtil;

@Service
public class ImageUploadService {
	private static final Logger LOGGER = LogManager.getLogger(ImageUploadService.class);
	public static final String CLIENT_ID = "d93c4f260e94fef";
	public static final String IMGUR_URL = "https://api.imgur.com/3/image";
	private final ImageUtil imageUtil;

	public ImageUploadService(ImageUtil imageUtil) {
		this.imageUtil = imageUtil;
	}

	public ImageResponse uploadImage(MultipartFile multipartFile, String userId) throws UploadException {
		String base64String = imageUtil.getBase64String(multipartFile);
		ResponseObject responseObject = uploadImage(base64String, multipartFile.getName());

		return ImageResponse.builder()
				.status(Status.builder()
						.code(responseObject.getStatusCode())
						.message(responseObject.isSuccess() ? "Success": "Failed")
						.build())
				.build();
	}

	public ImageResponse processImageUpload(UrlObject urlObject, String userId) throws UploadException {
		List<String> links = urlObject.getUrls();
		if (null != links && links.size() > 0) {
			links.stream().forEach(link -> {
				try {
					String base64String = imageUtil.getBase64String(link);
					ResponseObject responseObject = uploadImage(base64String, link);
				} catch (UploadException ex) {
					LOGGER.error(ex);
				}
			});
		}

		return ImageResponse.builder()
				.status(Status.builder()
						.code(200)
						.message("Success")
						.build())
				.build();
	}

	private ResponseObject uploadImage(String base64String, String imageLink) throws UploadException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPostRequest = new HttpPost(IMGUR_URL);
		httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("image", base64String));
		CustomResponseHandler customResponseHandler = new CustomResponseHandler();
		int status = -1;
		ResponseObject responseBody;
		try {
			httpPostRequest.setEntity(new UrlEncodedFormEntity(params));
			responseBody = (ResponseObject) httpClient.execute(httpPostRequest, customResponseHandler);
			LOGGER.info("imgur response " + responseBody);

			status = responseBody.getStatusCode();
			if (status >= 200 && status < 300) {
				LOGGER.info("Uploaded " + imageLink + " to imgur.");
			} else {
				LOGGER.info("Failed to upload " + imageLink + " to imgur");
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
