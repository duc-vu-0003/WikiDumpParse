package com.ducva.main;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class SAXPageCallbackHandler extends DefaultHandler {
	private PageCallbackHandler pageHandler;
	private WikiPage currentPage;
	private String currentTag;

	private StringBuilder currentWikitext;
	private StringBuilder currentTitle;
	private StringBuilder currentID;

	public SAXPageCallbackHandler(PageCallbackHandler ph) {
		pageHandler = ph;
	}

	public void startElement(String uri, String name, String qName, Attributes attr) {
		currentTag = qName;
		if (qName.equals("page")) {
			currentPage = new WikiPage();
			currentWikitext = new StringBuilder("");
			currentTitle = new StringBuilder("");
			currentID = new StringBuilder("");
		}
	}

	public void endElement(String uri, String name, String qName) {
		if (qName.equals("page")) {
			currentPage.setTitle(currentTitle.toString());
			currentPage.setID(currentID.toString());
			currentPage.setWikiText(currentWikitext.toString());
			pageHandler.process(currentPage);
		}
		if (qName.equals("mediawiki")) {
			// TODO hasMoreElements() should now return false
		}
	}

	public void characters(char ch[], int start, int length) {
		if (currentTag.equals("title")) {
			currentTitle = currentTitle.append(ch, start, length);
		}
		else if ((currentTag.equals("id")) && (currentID.length() == 0)) {
			currentID = new StringBuilder();
			currentID.append(ch, start, length);
		} else if (currentTag.equals("text")) {
			currentWikitext = currentWikitext.append(ch, start, length);
		}
	}
}
