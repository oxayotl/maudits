package com.maudits.website.domain.page;

import com.maudits.website.repository.entities.Edition;

import lombok.Getter;

@Getter
public class FrontPageDisplayer {
	private final String editionTimePeriod;
	private final String editionName;
	private final String editorialTitle;
	private final String editorial;
	private final String teaserVideoUrl;

	public FrontPageDisplayer(Edition edition) {
		this.editionTimePeriod = edition.getTimePeriod();
		this.editionName = edition.getName();
		this.editorialTitle = edition.getEditorialTitle();
		this.editorial = edition.getEditorial();
		this.teaserVideoUrl = edition.getTeaserUrl();
	}
}
