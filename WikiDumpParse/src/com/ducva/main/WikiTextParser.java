package com.ducva.main;

import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class WikiTextParser {
	
	private String wikiText = null;
	
	public WikiTextParser(String wtext) {
		this.wikiText = wtext;  
	}
	
	public String getPlainText() {
		String text = wikiText.replaceAll("&gt;", ">");
		text = text.replaceAll("&lt;", "<");
		text = text.replaceAll("<ref>.*?</ref>", " ");
		text = text.replaceAll("</?.*?>", " ");
		text = text.replaceAll("\\{\\{.*?\\}\\}", " ");
		text = text.replaceAll("\\[\\[.*?:.*?\\]\\]", " ");
		text = text.replaceAll("\\[\\[(.*?)\\]\\]", "$1");
		text = text.replaceAll("\\s(.*?)\\|(\\w+\\s)", " $2");
		text = text.replaceAll("\\[.*?\\]", " ");
		text = text.replaceAll("\\'+", "");
		text = text.replaceAll("=", "");
		return text;
	}

	public String getText() {
		return wikiText;
	}
	
	public String getRealText() {
		String text = getPlainText();
		
		if (text.contains("http")) {
			return "";
		}
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		// read script file
		try {
			FileReader reader = new FileReader("resource/txtwiki.js");
			engine.eval(reader);
		    reader.close();
			Invocable inv = (Invocable) engine;
			Object result = inv.invokeFunction("parseWikitext", new Object[] { text });
			
			text = result.toString();
		} catch (ScriptException | IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		}
		return text;
	}
}
