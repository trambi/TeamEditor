/*
Copyright (C) 2010  Bertrand MADET

This org.tisseursdechimeres.bb.teameditor is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.teameditor is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.teameditor.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter.bbcode;

public class BBCodeUtils {
	
	public static String toSimpleText(String toConvert){
		String converted = new String(toConvert);
		
		converted = converted.replace("ä","a");
		converted = converted.replace("â","a");
		converted = converted.replace("à","a");
		converted = converted.replace("Ä","A");
		converted = converted.replace("Â","A");
		converted = converted.replace("À","A");
		
		converted = converted.replace("ç","c");
		converted = converted.replace("Ç","C");
		
		converted = converted.replace("é","e");
		converted = converted.replace("è","e");
		converted = converted.replace("ë","e");
		converted = converted.replace("ê","e");
		converted = converted.replace("È","E");
		converted = converted.replace("É","E");
		converted = converted.replace("Ë","E");
		converted = converted.replace("Ê","E");
		
		converted = converted.replace("î","i");
		converted = converted.replace("ï","i");
		converted = converted.replace("Î","I");
		converted = converted.replace("Ï","I");
		
		converted = converted.replace("ö","o");
		converted = converted.replace("ô","o");
		converted = converted.replace("Ö","O");
		converted = converted.replace("Ô","O");
		
		converted = converted.replace("ù","u");
		converted = converted.replace("ü","u");
		converted = converted.replace("û","u");
		converted = converted.replace("Ù","U");
		converted = converted.replace("Ü","U");
		converted = converted.replace("Û","U");
		
		return converted;
	}
}
