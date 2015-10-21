/*
Copyright (C) 2010  Bertrand MADET

This org.tisseursdechimeres.bb.test is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.test is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.test.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.tisseursdechimeres.bb.SkillCategory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import junit.framework.TestCase;

public class SkillCategoryTest extends TestCase {
/*
 * 
 * 	public static final String XML_TAG= "skill_category";
	public static final String XML_NAME_ATTRIBUTE= "name";
	public static final String XML_ALWAYS_AVAILABLE_ATTRIBUTE= "always_available";
		
 */
	public void testExtractFromElement(){
		SkillCategory extracted = new SkillCategory();
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document doc = constructeur.newDocument();
			Element elt = doc.createElement(SkillCategory.XML_TAG);
			elt.setTextContent("Test content");
			elt.setAttribute(SkillCategory.XML_NAME_ATTRIBUTE,"Test content");
			elt.setAttribute(SkillCategory.XML_ALWAYS_AVAILABLE_ATTRIBUTE,String.valueOf(true));
			extracted.extractFromElement(elt);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		assertEquals(extracted.getName(),"Test content");
		assertEquals(extracted.getAlwaysAvailable(),new Boolean(true));
		
	}
	
}
