/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import org.tisseursdechimeres.bb.Team;

public interface TeamFileReader {
	public FileFilter getInputFileFilter();
	public void setTeamToRead(Team team);
	public boolean readFile(File fileToRead) throws Exception;
}