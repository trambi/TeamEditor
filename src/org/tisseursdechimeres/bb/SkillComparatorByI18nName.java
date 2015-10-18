/*
Copyright (C) 2010-2011  Bertrand MADET

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

import java.util.Comparator;

public class SkillComparatorByI18nName implements Comparator<Skill> {
	
	@Override
	public int compare(Skill s1, Skill s2) {
		String i18nName1,i18nName2;
		i18nName1 = s1.getI18nName();
		i18nName2 = s2.getI18nName();

		return i18nName1.compareTo(i18nName2);
	}
}
