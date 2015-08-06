package com.ducva.main;

public class WikiPage {
	private String title = null;
	private WikiTextParser wikiTextParser = null;
	private String id = null;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWikiText(String wtext) {
		wikiTextParser = new WikiTextParser(wtext);
	}

	public String getTitle() {
		return title;
	}

	public String getWikiText() {
		return wikiTextParser.getText();
	}

	public String getText() {
		return wikiTextParser.getRealText();
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}
}
