package com.maudits.website.thymeleaf;

import org.thymeleaf.expression.Strings;

public class BoldAndItalicFormatAttributeTagProcessor extends AbstractTextAttributeTagProcessor {

	private static final String ATTR_NAME = "bolditalics";

	public BoldAndItalicFormatAttributeTagProcessor(final String dialectPrefix) {
		super(dialectPrefix, ATTR_NAME);
	}

	@Override
	protected String processString(String attributeContent) {
		String escapedString = new Strings(null).escapeXml(attributeContent);
		String lineBreakString = escapedString.replace(System.getProperty("line.separator"), "<br />");
		return lineBreakString.replaceAll("\\[i](.*?)\\[/i]", "<i>$1</i>").replaceAll("\\[b](.*?)\\[/b]", "<b>$1</b>");
	}

}