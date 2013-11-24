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
package net.wexoo.organicdroidormlite.proxy;

import java.util.List;

import net.wexoo.organicdroidormlite.condition.Parenthesis;
import net.wexoo.organicdroidormlite.condition.SQLiteCondition;
import net.wexoo.organicdroidormlite.entity.DatabaseEntity;

/**
 * AbstractProxy.java
 * 
 * @author wexoo
 */
public abstract class AbstractProxy {

	public abstract <T extends DatabaseEntity> void saveOrUpdateEntity(T entity, Class<T> entityClass);

	public abstract <T extends DatabaseEntity> void saveOrUpdateEntities(List<T> entities, Class<T> entityClass);

	@SuppressWarnings("unused")
	private String buildConditionString(final SQLiteCondition... conditions) {
		String conditionString = "";

		for (final SQLiteCondition cond : conditions) {
			conditionString += " " + cond.getOperator().name() + " ";
			if (cond.getParenthesis().equals(Parenthesis.OPEN)) {
				conditionString += cond.getParenthesis().toString();
			}
			conditionString += cond.getCondition();
			if (cond.getParenthesis().equals(Parenthesis.CLOSE)) {
				conditionString += cond.getParenthesis().toString();
			}
		}
		return conditionString;
	}
}