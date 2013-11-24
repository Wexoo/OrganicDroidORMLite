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
package net.wexoo.organicdroidormlite.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * DatabaseEntity.java
 * 
 * @author wexoo
 */
public class DatabaseEntity {

   public static final String ID_COLUMN = "id";

   @DatabaseField(generatedId = true, columnName = ID_COLUMN)
   private Integer id;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }
}
