/***
 * Copyright (C) 2013 wexoo
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

package net.wexoo.organicdroidormlite.base;

import java.sql.SQLException;
import java.util.ArrayList;

import net.wexoo.organicdroid.Log;
import net.wexoo.organicdroid.base.BaseApplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * DBHelper.java
 * 
 * @author wexoo
 */
public class BaseDBHelper extends OrmLiteSqliteOpenHelper {
	
	protected static final String TAG = BaseDBHelper.class.getSimpleName();
	
	/**
	 * Instantiates a new dB helper.
	 * 
	 * @param context the context
	 */
	public BaseDBHelper(final Context context) {
		super(context, BaseApplication.getDatabaseName(), null, BaseApplication.getDatabaseVersion());
	}
	
	/**
	 * Called to create the DB
	 */
	@Override
	public void onCreate(final SQLiteDatabase db, final ConnectionSource source) {
		createTables(source, getEntityClasses(new ArrayList<Class<?>>()));
	}
	
	protected void createTables(ConnectionSource source, ArrayList<Class<?>> entityClasses) {
		try {
			for (Class<?> entityClass : entityClasses) {
				TableUtils.createTable(source, entityClass);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Override in project to specify entity classes needed in project and db
	 */
	protected ArrayList<Class<?>> getEntityClasses(ArrayList<Class<?>> arrayList) {
		return arrayList;
	}
	
	/**
	 * Drop and create table.
	 * 
	 * @param entityClass the entity class
	 */
	public void dropAndCreateTable(final Class<?> entityClass) {
		try {
			TableUtils.dropTable(getConnectionSource(), entityClass, true);
			TableUtils.createTable(getConnectionSource(), entityClass);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Invoked if a DB upgrade (version change) has been detected.
	 * 
	 * @param oldVersion the last version of the database
	 * @param newVersion the new or current version of the database
	 */
	
	@Override
	public void onUpgrade(final SQLiteDatabase db, final ConnectionSource connectionSource, final int oldVersion,
				final int newVersion) {
		
		String message = "New SQLite Version!\nPrevious: " + oldVersion + "\nNew:" + newVersion;
		
		Toast.makeText(BaseApplication.mainContext, message, Toast.LENGTH_LONG).show();
		Log.w(TAG, message);
	}
}