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

public class SkillComparatorByCategory implements Comparator<Skill> {
	@Override
	public int compare(Skill s1, Skill s2) {
		Integer category1,category2;
		int comparaison;
		category1 = s1.getCategoryIndex();
		category2 = s2.getCategoryIndex();
		comparaison = category1.compareTo(category2);
		if(comparaison == 0){
			String i18nName1,i18nName2;
			i18nName1 = s1.getI18nName();
			i18nName2 = s2.getI18nName();
			comparaison = i18nName1.compareTo(i18nName2);
		}
		return comparaison;
	}
}
