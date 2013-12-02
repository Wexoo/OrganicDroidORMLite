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

import net.wexoo.organicdroid.Log;
import net.wexoo.organicdroid.base.BaseActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;

/**
 * BaseOrmLiteActivity.java
 * 
 * @author wexoo
 */
@SuppressLint("InlinedApi")
public class BaseOrmLiteActivity<H extends OrmLiteSqliteOpenHelper> extends BaseActivity {
	
	private static final String TAG = BaseOrmLiteActivity.class.getSimpleName();
	private volatile H helper;
	private volatile boolean created = false;
	private volatile boolean destroyed = false;
	private static Logger logger = LoggerFactory.getLogger(OrmLiteBaseActivity.class);
	
	/**
	 * Get a helper for this action.
	 */
	public H getHelper() {
		if (helper == null) {
			if (!created)
				throw new IllegalStateException("A call has not been made to onCreate() yet so the helper is null");
			else if (destroyed)
				throw new IllegalStateException(
							"A call to onDestroy has already been made and the helper cannot be used after that point");
			else
				throw new IllegalStateException("Helper is null for some unknown reason");
		} else
			return helper;
	}
	
	/**
	 * Get a connection source for this action.
	 */
	public ConnectionSource getConnectionSource() {
		return getHelper().getConnectionSource();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initializeDBHelper();
		super.onCreate(savedInstanceState);
	}
	
	protected void initializeDBHelper() {
		if (helper == null) {
			helper = getHelperInternal(this);
			created = true;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		cleanUpDBHelper();
	}
	
	private void cleanUpDBHelper() {
		releaseHelper(helper);
		destroyed = true;
	}
	
	/**
	 * This is called internally by the class to populate the helper object instance. This should not be called directly
	 * by client code unless you know what you are doing. Use {@link #getHelper()} to get a helper instance. If you are
	 * managing your own helper creation, override this method to supply this activity with a helper instance.
	 * <p>
	 * <b> NOTE: </b> If you override this method, you most likely will need to override the
	 * {@link #releaseHelper(OrmLiteSqliteOpenHelper)} method as well.
	 * </p>
	 */
	protected H getHelperInternal(Context context) {
		@SuppressWarnings({"unchecked", "deprecation"})
		H newHelper = (H) OpenHelperManager.getHelper(context);
		Log.d(TAG, "{}: got new helper {} from OpenHelperManager");
		return newHelper;
	}
	
	/**
	 * Release the helper instance created in {@link #getHelperInternal(Context)}. You most likely will not need to call
	 * this directly since {@link #onDestroy()} does it for you.
	 * <p>
	 * <b> NOTE: </b> If you override this method, you most likely will need to override the
	 * {@link #getHelperInternal(Context)} method as well.
	 * </p>
	 */
	protected void releaseHelper(H helper) {
		OpenHelperManager.releaseHelper();
		Log.d(TAG, "{}: helper {} was released, set to null");
		this.helper = null;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "@" + Integer.toHexString(super.hashCode());
	}
}
