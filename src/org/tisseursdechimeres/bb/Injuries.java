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

public class Injuries extends ArrayList<Injury> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 573044909093902674L;

	public String getSummary() {
		String summary = new String();
		for(Injury injury : this ){
			summary += injury.getI18nSymbol();
		}
		return summary;
	}
	
	@Override
	public String toString(){
		return getSummary();
	}
	
	public String getI18nNameList() {
		String i18nInjuries = new String();
		boolean isFirst = true;
		for(Injury inj : this){
			if(isFirst)
				isFirst = false;
			else
				i18nInjuries += ", ";
			i18nInjuries += inj.getI18nName();
		}
		return i18nInjuries;
	}

}
