package com.mobile.food.facility.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.mobile.food.facility.constants.MessageCodes;
import com.mobile.food.facility.dto.response.BaseResponse;
import com.mobile.food.facility.dto.response.StatusMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalException {

	/**
	 * This method used to throw server internal error at global level
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> generalException(Exception e) {
		log.debug("Exception handler for handling Exception");
		log.error("Exception occured - ", e);
		BaseResponse model = new BaseResponse();
		model.setStatus(MessageCodes.INTERNAL_SERVER_ERROR);
		model.setStatusMessage(new StatusMessage(MessageCodes.INTERNAL_SERVER_ERROR_MSG,
				"Internal Server Error. Please try again later."));
		return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method used to send max upload size exception error at global level
	 * 
	 * @param e
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<BaseResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
		log.debug("Exception handler for handling Exception");
		log.error("Exception occured - ", e);
		BaseResponse model = new BaseResponse();
		model.setStatus(MessageCodes.BAD_REQUEST);
		model.setStatusMessage(
				new StatusMessage(MessageCodes.BAD_REQUEST_MSG, e.getCause().getCause().getLocalizedMessage()));
		return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method used to send bad request response error at global level
	 * 
	 * @param e
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponse> badRequest(MethodArgumentNotValidException e) {
		log.debug("Exception handler for handling MethodArgumentNotValidException");
		log.error("MethodArgumentNotValidException occured - ", e);
		BaseResponse model = new BaseResponse();
		model.setStatus(MessageCodes.BAD_REQUEST);
		model.setStatusMessage(new StatusMessage(MessageCodes.BAD_REQUEST_MSG, e.getMessage()));
		return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method used to send custom exception error messages at global level
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<BaseResponse> customException(CustomException e) {
		log.debug("Exception handler for handling CustomException");
		log.error("CustomException occured - ", e);
		BaseResponse model = new BaseResponse();
		if (e.getMessage().equalsIgnoreCase(MessageCodes.BAD_REQUEST)) {
			model.setStatus(MessageCodes.BAD_REQUEST);
			model.setStatusMessage(new StatusMessage(MessageCodes.BAD_REQUEST_MSG, MessageCodes.BAD_REQUEST_DESC));
			return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			model.setStatus(e.getStatus());
			model.setStatusMessage(new StatusMessage(e.getStatus(), e.getMessage()));
			return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method used to send bad request response error at global level
	 * 
	 * @param e
	 * @return
	 * @throws IOException
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<BaseResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		log.debug("Exception handler for handling IllegalArgumentException");
		log.error("IllegalArgumentException occured - ", e);
		BaseResponse model = new BaseResponse();
		model.setStatus(MessageCodes.BAD_REQUEST);
		if (e.getMessage().equals("500")) {
			model.setStatusMessage(new StatusMessage(MessageCodes.BAD_REQUEST_MSG, MessageCodes.BAD_REQUEST_DESC));
		} else {
			model.setStatusMessage(new StatusMessage(MessageCodes.BAD_REQUEST_MSG, e.getMessage()));
		}
		return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
	}

}
