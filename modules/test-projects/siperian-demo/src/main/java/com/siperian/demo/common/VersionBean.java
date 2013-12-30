package com.siperian.demo.common;

import org.richfaces.VersionBean.Version;

/**
 * @author Ilya Shaikovsky
 *
 */
public class VersionBean {
	public static final String VENDOR = "Exadel";
	public static final int MAJOR_VERSION = 1;
	public static final int MINOR_VERSION = 1;
	public static final String PROJECT_NAME = "Siperian Demo";
	
	/**
	 * Revision version, must be auto modified by CVS 
	 */
	
	public static final String REVISION = "7.612" ;
	public static final Version _version = new Version();
	
	public String getVendor() {
		return VENDOR;
	}
	
	public Version getVersion() {
		return _version;
	}

	public String getProjectName() {
		return PROJECT_NAME;
	}
	
	/**
	 * Class for incapsulate version info.
	 * @author asmirnov@exadel.com (latest modification by $Author: nbelaevski $)
	 *
	 */
	public static class Version {
		
		public static final String _versionInfo = "v."+MAJOR_VERSION+"."+MINOR_VERSION+"."+REVISION;

		public int getMajor() {
			return MAJOR_VERSION;
		}

		public int getMinor() {
			return MINOR_VERSION;
		}
		
		public String getRevision() {
			return REVISION;
		}
/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return _versionInfo;
		}
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return getProjectName() + " by "+getVendor()+", version "+getVersion().toString();
	}
}
