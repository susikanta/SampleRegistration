package com.sampleregistration.util;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Component;

import com.sampleregistration.exception.UploadException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

@Component
public class ImageUtil {
	private static final Logger LOGGER = LogManager.getLogger(ImageUtil.class);

	public String getBase64String(String link) throws UploadException {
		byte[] byteData;
		String base64;

		try (InputStream in = new URL(link).openStream()) {
			byteData = IOUtils.toByteArray(in);
			base64 = Base64.getEncoder().encodeToString(byteData);
			LOGGER.info(link + " has been downloaded now. Base64 length: " + base64.length());
		} catch (MalformedURLException e) {
			throw new UploadException(e, 555);
		} catch (IOException e) {
			throw new UploadException(e);
		}

		return base64;
	}
	
	public String getBase64String(MultipartFile multipartFile) throws UploadException {
		byte[] byteData;
		String base64;

		try (InputStream in = multipartFile.getInputStream()) {
			byteData = IOUtils.toByteArray(in);
			base64 = Base64.getEncoder().encodeToString(byteData);
			LOGGER.info(multipartFile.getName() + " has been downloaded now. Base64 length: " + base64.length());
		} catch (MalformedURLException e) {
			throw new UploadException(e, 555);
		} catch (IOException e) {
			throw new UploadException(e);
		}

		return base64;
	}
}
