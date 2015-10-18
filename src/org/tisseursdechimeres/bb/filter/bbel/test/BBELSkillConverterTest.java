/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.bbel.test is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.bbel.test is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.bbel.test.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.tisseursdechimeres.bb.filter.bbel.test;

import org.tisseursdechimeres.bb.Skill;
import org.tisseursdechimeres.bb.filter.bbel.BBELSkillConverter;

import junit.framework.TestCase;

public class BBELSkillConverterTest extends TestCase {
	
	public void testStatIncreasesFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		
		skill.setName("strength_bonus");
		converter.fromSkill(skill);
		assertEquals(2,converter.getIndex());
		
		skill.setName("agility_bonus");
		converter.fromSkill(skill);
		assertEquals(3,converter.getIndex());

		skill.setName("movement_bonus");
		converter.fromSkill(skill);
		assertEquals(4,converter.getIndex());
		
		skill.setName("armour_bonus");
		converter.fromSkill(skill);
		assertEquals(5,converter.getIndex());
	}
	
	public void testGeneralFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		int converted = 0;
		
		skill.setName("strip_ball");
		converter.fromSkill(skill);
		assertEquals(1,converter.getIndex());
		converted++;
				
		skill.setName("pass_block");
		converter.fromSkill(skill);
		assertEquals(9,converter.getIndex());
		converted++;
		
		skill.setName("dauntless");
		converter.fromSkill(skill);
		assertEquals(26,converter.getIndex());
		converted++;
		
		skill.setName("dirty_player");
		converter.fromSkill(skill);
		assertEquals(27,converter.getIndex());
		converted++;
		
		skill.setName("block");
		converter.fromSkill(skill);
		assertEquals(30,converter.getIndex());
		converted++;
		
		skill.setName("fend");
		converter.fromSkill(skill);
		assertEquals(35,converter.getIndex());
		converted++;
		
		skill.setName("frenzy");
		converter.fromSkill(skill);
		assertEquals(36,converter.getIndex());
		converted++;

		skill.setName("pro");
		converter.fromSkill(skill);
		assertEquals(50,converter.getIndex());
		converted++;
		
		skill.setName("shadowing");
		converter.fromSkill(skill);
		assertEquals(55,converter.getIndex());
		converted++;
		
		skill.setName("tackle");
		converter.fromSkill(skill);
		assertEquals(57,converter.getIndex());
		converted++;
		
		skill.setName("sure_hands");
		converter.fromSkill(skill);
		assertEquals(61,converter.getIndex());
		converted++;
		
		skill.setName("wrestle");
		converter.fromSkill(skill);
		assertEquals(68,converter.getIndex());
		converted++;
		
		skill.setName("kick");
		converter.fromSkill(skill);
		assertEquals(71,converter.getIndex());
		converted++;
		
		skill.setName("kick_off_return");
		converter.fromSkill(skill);
		assertEquals(72,converter.getIndex());
		converted++;
		
		assertEquals(14,converted);
	}
	
	public void testAgilityFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		int converted = 0;
		
		skill.setName("catch");
		converter.fromSkill(skill);
		assertEquals(6,converter.getIndex());
		converted++;
		
		skill.setName("dodge");
		converter.fromSkill(skill);
		assertEquals(7,converter.getIndex());
		converted++;
		
		skill.setName("sprint");
		converter.fromSkill(skill);
		assertEquals(8,converter.getIndex());
		converted++;
		
		skill.setName("leap");
		converter.fromSkill(skill);
		assertEquals(11,converter.getIndex());
		converted++;
		
		skill.setName("sneaky_git");
		converter.fromSkill(skill);
		assertEquals(23,converter.getIndex());
		converted++;
		
		skill.setName("diving_catch");
		converter.fromSkill(skill);
		assertEquals(28,converter.getIndex());
		converted++;
		
		skill.setName("diving_tackle");
		converter.fromSkill(skill);
		assertEquals(34,converter.getIndex());
		converted++;
		
		skill.setName("jump_up");
		converter.fromSkill(skill);
		assertEquals(41,converter.getIndex());
		converted++;
		
		skill.setName("side_step");
		converter.fromSkill(skill);
		assertEquals(56,converter.getIndex());
		converted++;
		
		skill.setName("sure_feet");
		converter.fromSkill(skill);
		assertEquals(60,converter.getIndex());
		converted++;
		
		assertEquals(10,converted);
		
	}
	
	public void testStrengthFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		int converted = 0;
		
		skill.setName("mighty_blow");
		converter.fromSkill(skill);
		assertEquals(13,converter.getIndex());
		converted++;
		
		skill.setName("stand_firm");
		converter.fromSkill(skill);
		assertEquals(17,converter.getIndex());
		converted++;
		
		skill.setName("break_tackle");
		converter.fromSkill(skill);
		assertEquals(22,converter.getIndex());
		converted++;
		
		skill.setName("grab");
		converter.fromSkill(skill);
		assertEquals(37,converter.getIndex());
		converted++;
		
		skill.setName("guard");
		converter.fromSkill(skill);
		assertEquals(38,converter.getIndex());
		converted++;
		
		skill.setName("juggernaut");
		converter.fromSkill(skill);
		assertEquals(40,converter.getIndex());
		converted++;
		
		skill.setName("piling_on");
		converter.fromSkill(skill);
		assertEquals(48,converter.getIndex());
		converted++;
		
		skill.setName("strong_arm");
		converter.fromSkill(skill);
		assertEquals(58,converter.getIndex());
		converted++;
		
		skill.setName("thick_skull");
		converter.fromSkill(skill);
		assertEquals(63,converter.getIndex());
		converted++;
		
		skill.setName("multiple_block");
		converter.fromSkill(skill);
		assertEquals(70,converter.getIndex());
		converted++;
		
		assertEquals(10,converted);
	}
	
	public void testPassingFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		int converted = 0;
		
		skill.setName("leader");
		converter.fromSkill(skill);
		assertEquals(14,converter.getIndex());
		converted++;
		
		skill.setName("accurate");
		converter.fromSkill(skill);
		assertEquals(21,converter.getIndex());
		converted++;
		
		skill.setName("dump_off");
		converter.fromSkill(skill);
		assertEquals(29,converter.getIndex());
		converted++;
		
		skill.setName("hail_mary_pass");
		converter.fromSkill(skill);
		assertEquals(39,converter.getIndex());
		converted++;

		skill.setName("nerves_of_steel");
		converter.fromSkill(skill);
		assertEquals(45,converter.getIndex());
		converted++;
		
		skill.setName("pass");
		converter.fromSkill(skill);
		assertEquals(47,converter.getIndex());
		converted++;
		
		skill.setName("safe_throw");
		converter.fromSkill(skill);
		assertEquals(53,converter.getIndex());
		converted++;
		
		assertEquals(7,converted);
	}
	
	public void testMutationFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		int converted =0;
		
		skill.setName("foul_appearance");
		converter.fromSkill(skill);
		assertEquals(10,converter.getIndex());
		converted++;
		
		skill.setName("extra_arm");
		converter.fromSkill(skill);
		assertEquals(12,converter.getIndex());
		converted++;
		
		skill.setName("horns");
		converter.fromSkill(skill);
		assertEquals(15,converter.getIndex());
		converted++;
		
		skill.setName("two_heads");
		converter.fromSkill(skill);
		assertEquals(16,converter.getIndex());
		converted++;

		skill.setName("long_legs");
		converter.fromSkill(skill);
		assertEquals(32,converter.getIndex());
		converted++;
		
		skill.setName("disturbing_presence");
		converter.fromSkill(skill);
		assertEquals(33,converter.getIndex());
		converted++;
		
		skill.setName("prehensive_tail");
		converter.fromSkill(skill);
		assertEquals(49,converter.getIndex());
		converted++;
		
		skill.setName("tentacles");
		converter.fromSkill(skill);
		assertEquals(69,converter.getIndex());
		converted++;

		skill.setName("big_hand");
		converter.fromSkill(skill);
		assertEquals(74,converter.getIndex());
		converted++;
		
		skill.setName("claw");
		converter.fromSkill(skill);
		assertEquals(75,converter.getIndex());
		converted++;
		
		assertEquals(10,converted);
	}
	
	public void testExtraordinaryFrom(){
		Skill skill = new Skill();
		BBELSkillConverter converter = new BBELSkillConverter();
		int converted =0;
		skill.setName("always_hungry");
		converter.fromSkill(skill);
		assertEquals(18,converter.getIndex());
		converted++;
		
		skill.setName("regeneration");
		converter.fromSkill(skill);
		assertEquals(19,converter.getIndex());
		converted++;
		
		skill.setName("take_root");
		converter.fromSkill(skill);
		assertEquals(20,converter.getIndex());
		converted++;
		
		skill.setName("chainsaw");
		converter.fromSkill(skill);
		assertEquals(25,converter.getIndex());
		converted++;

		skill.setName("bone_head");
		converter.fromSkill(skill);
		assertEquals(31,converter.getIndex());
		converted++;
		
		skill.setName("loner");
		converter.fromSkill(skill);
		assertEquals(44,converter.getIndex());
		converted++;
		
		skill.setName("no_hands");
		converter.fromSkill(skill);
		assertEquals(46,converter.getIndex());
		converted++;
		
		skill.setName("really_stupid");
		converter.fromSkill(skill);
		assertEquals(51,converter.getIndex());
		converted++;

		skill.setName("right_stuff");
		converter.fromSkill(skill);
		assertEquals(52,converter.getIndex());
		converted++;
		
		skill.setName("secret_weapon");
		converter.fromSkill(skill);
		assertEquals(54,converter.getIndex());
		converted++;
		
		skill.setName("stunty");
		converter.fromSkill(skill);
		assertEquals(59,converter.getIndex());
		converted++;
		
		skill.setName("throw_team_mate");
		converter.fromSkill(skill);
		assertEquals(64,converter.getIndex());
		converted++;
		
		skill.setName("wild_animal");
		converter.fromSkill(skill);
		assertEquals(67,converter.getIndex());
		converted++;
		
		skill.setName("ball_chain");
		converter.fromSkill(skill);
		assertEquals(76,converter.getIndex());
		converted++;

		skill.setName("stab");
		converter.fromSkill(skill);
		assertEquals(77,converter.getIndex());
		converted++;
		
		skill.setName("hypnotic_gaze");
		converter.fromSkill(skill);
		assertEquals(78,converter.getIndex());
		converted++;
		
		skill.setName("stakes");
		converter.fromSkill(skill);
		assertEquals(79,converter.getIndex());
		converted++;
		
		skill.setName("bombardier");
		converter.fromSkill(skill);
		assertEquals(80,converter.getIndex());
		converted++;
		
		skill.setName("decay");
		converter.fromSkill(skill);
		assertEquals(81,converter.getIndex());
		converted++;
		
		skill.setName("nurgles_rot");
		converter.fromSkill(skill);
		assertEquals(82,converter.getIndex());
		converted++;
		
		skill.setName("titchy");
		converter.fromSkill(skill);
		assertEquals(83,converter.getIndex());
		converted++;
		
		skill.setName("blood_lust");
		converter.fromSkill(skill);
		assertEquals(84,converter.getIndex());
		converted++;
		
		skill.setName("fan_favourite");
		converter.fromSkill(skill);
		assertEquals(85,converter.getIndex());
		converted++;
		
		assertEquals(23,converted);
	}
}
