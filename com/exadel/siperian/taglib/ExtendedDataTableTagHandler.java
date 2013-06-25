/**
 * GENERATED FILE - DO NOT EDIT
 *
 */

package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import org.richfaces.model.selection.Selection ;
import javax.faces.convert.Converter ;
import java.lang.Object ;
import java.lang.Boolean ;
import java.util.List ;
import org.ajax4jsf.model.DataComponentState ;
import java.lang.Integer ;
import java.lang.String ;
import java.util.Set ;
import com.exadel.siperian.taglib.ExtendedDataTableTagHandlerBase ;
import java.util.Collection ;
import javax.faces.component.UIComponent;
import com.exadel.siperian.component.html.HtmlExtendedDataTable;
import com.sun.facelets.tag.jsf.ComponentHandler;
import com.sun.facelets.tag.jsf.ComponentConfig;

import com.sun.facelets.*;
import com.sun.facelets.el.*;
import com.sun.facelets.tag.*;
/**
 * @author shura (latest modification by $Author: alexsmirnov $)
 * @version $Revision: 1.1.2.1 $ $Date: 2007/02/26 20:48:51 $
 *
 */
public class ExtendedDataTableTagHandler extends com.exadel.siperian.taglib.ExtendedDataTableTagHandlerBase {


  private static final ExtendedDataTableTagHandlerMetaRule metaRule = new ExtendedDataTableTagHandlerMetaRule();


  
  public ExtendedDataTableTagHandler(ComponentConfig config) 
  {
    super(config);
  }
// Metarule
  protected MetaRuleset createMetaRuleset(Class type)
  {
    MetaRuleset m = super.createMetaRuleset(type);
	m.addRule(metaRule);
	return m;
  }

  	/**
	 * @author shura (latest modification by $Author: alexsmirnov $)
	 * @version $Revision: 1.1.2.1 $ $Date: 2007/02/26 20:48:51 $
	 *
	 */
	static class ExtendedDataTableTagHandlerMetaRule extends MetaRule{

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.MetaRule#applyRule(java.lang.String, com.sun.facelets.tag.TagAttribute, com.sun.facelets.tag.MetadataTarget)
		 */
		public Metadata applyRule(String name, TagAttribute attribute, MetadataTarget meta) {
	        if (meta.isTargetInstanceOf(HtmlExtendedDataTable.class)) {
				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 				 		  		 				 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 				 				 				 		  		 				 				 				 				 				 				 		  		 				 		  		 				 				 				 				 				 				 		  		 				 				 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 		  		 				 				 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 				 		  		 			        }
			return null;
		}

	}


    
    
    
     
    
    
    
     
    
    
     
    
     
    
    
     
     
    
     
    
    
    
    
    
      
    
    
    
     
     
    
    
    
    
    
    
    
         
         
    
         
       
    
    
    
    
     
    
    
    
    
    
    
    
      
      
    
    
     
    
     
    }
