/***
 * Copyright (C) 2011  wexoo
 * p.weixlbaumer@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.wexoo.organicdroidormlite.condition;

/**
 * SQLiteCondition.java
 * 
 * @author wexoo
 */
public class SQLiteCondition {

	private LogicalOperator operator;

	private String condition;

	private Parenthesis parenthesis;

	public SQLiteCondition(final LogicalOperator operator, final String condition) {
		this.operator = operator;
		this.condition = condition;
		this.parenthesis = Parenthesis.NONE;
	}

	public SQLiteCondition(final LogicalOperator operator, final String condition, final Parenthesis parenthesis) {
		this.operator = operator;
		this.condition = condition;
		this.parenthesis = parenthesis;
	}

	public LogicalOperator getOperator() {
		return this.operator;
	}

	public String getCondition() {
		return this.condition;
	}

	public Parenthesis getParenthesis() {
		return this.parenthesis;
	}

	public void setOperator(final LogicalOperator operator) {
		this.operator = operator;
	}

	public void setCondition(final String condition) {
		this.condition = condition;
	}

	public void setParenthesis(final Parenthesis parenthesis) {
		this.parenthesis = parenthesis;
	}
}
