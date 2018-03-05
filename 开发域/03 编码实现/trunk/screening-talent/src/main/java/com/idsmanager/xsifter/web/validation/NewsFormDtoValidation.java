package com.idsmanager.xsifter.web.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.idsmanager.xsifter.domain.news.Category;
import com.idsmanager.xsifter.service.dto.news.NewsFormDto;

@Component
public class NewsFormDtoValidation implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return NewsFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewsFormDto formDto = (NewsFormDto) target;
		validateTitle(formDto, errors);
		validateCategory(formDto, errors);
		validateContent(formDto, errors);
		validatePictureFile(formDto, errors);
	}

	private void validatePictureFile(NewsFormDto formDto, Errors errors) {
		final String pictureGuid = formDto.getPictureGuid();
		if (StringUtils.isEmpty(pictureGuid)) {

			final MultipartFile file = formDto.getPictureFile();
			if (file != null && !file.isEmpty()) {
				if (file.getContentType().indexOf("image/jpeg") < 0
						|| file.getSize() > 2 * 1048576) {
					errors.rejectValue("pictureGuid", null, "请检查您上传的附件格式和大小！");
				}
			}
		}

	}

	private void validateContent(NewsFormDto formDto, Errors errors) {
		final String content = formDto.getContent();
		if (StringUtils.isEmpty(content)) {
			errors.rejectValue("content", null, "新闻内容不能为空");
			return;
		}

	}

	private void validateCategory(NewsFormDto formDto, Errors errors) {
		final Category category = formDto.getCategory();
		if (category == null) {
			errors.rejectValue("category", null, "类别不能为空");
			return;
		}

	}

	private void validateTitle(NewsFormDto formDto, Errors errors) {
		final String title = formDto.getTitle();
		if (StringUtils.isEmpty(title)) {
			errors.rejectValue("title", null, "新闻标题不能为为空");
			return;
		}

	}

}
