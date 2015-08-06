package com.ducva.main;

public class Constant {
	public static final String PATH = "/Volumes/BENO/viwiki-20150702-pages-meta-current.xml";

	public static final String[] DISCARD_ELETEMENT = { "gallery", "timeline", "noinclude", "pre", "table", "tr", "td",
			"th", "caption", "div", "form", "input", "select", "option", "textarea", "ul", "li", "ol", "dl", "dt",
			"dd", "menu", "dir", "ref", "references", "img", "imagemap", "source", "small" };

	public static final String[] selfClosingTags = { "br", "hr", "nobr", "ref", "references", "nowiki" };

	public static final String[] ignoredTags = { "abbr", "b", "big", "blockquote", "center", "cite", "div", "em",
			"font", "h1", "h2", "h3", "h4", "hiero", "i", "kbd", "nowiki", "p", "plaintext", "s", "span", "strike",
			"strong", "sub", "sup", "tt", "u", "var" };
	public static final String[] placeholder_tags = { "math", "formula", "code", "codice" };

}
