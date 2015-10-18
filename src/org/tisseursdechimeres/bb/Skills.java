/*
Copyright (C) 2010  Bertrand MADET

This org.tisseursdechimeres.bb is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tisseursdechimeres.bb;

import java.util.ArrayList;

public class Skills extends ArrayList<Skill> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8064350000795682653L;

	public String getI18nNameList() {
		String i18nSkills = new String();
		boolean isFirst = true;
		for(Skill skill : this){
			if(isFirst)
				isFirst = false;
			else
				i18nSkills += ", ";
			i18nSkills += skill.getI18nName();
		}
		return i18nSkills;
	}
}
