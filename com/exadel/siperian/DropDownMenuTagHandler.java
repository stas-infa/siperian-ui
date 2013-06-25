/**
 * GENERATED FILE - DO NOT EDIT
 *
 */

package com.exadel.siperian;

import com.sun.facelets.tag.jsf.ComponentHandler ;
import javax.faces.component.UIComponent ;
import java.lang.Object ;
import java.lang.Integer ;
import java.lang.String ;
import javax.faces.component.UIComponent;
import com.exadel.siperian.component.html.HtmlDropDownMenu;
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
public class DropDownMenuTagHandler extends com.sun.facelets.tag.jsf.ComponentHandler {


  private static final DropDownMenuTagHandlerMetaRule metaRule = new DropDownMenuTagHandlerMetaRule();


  
  public DropDownMenuTagHandler(ComponentConfig config) 
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
	static class DropDownMenuTagHandlerMetaRule extends MetaRule{

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.MetaRule#applyRule(java.lang.String, com.sun.facelets.tag.TagAttribute, com.sun.facelets.tag.MetadataTarget)
		 */
		public Metadata applyRule(String name, TagAttribute attribute, MetadataTarget meta) {
	        if (meta.isTargetInstanceOf(HtmlDropDownMenu.class)) {
				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 		  		 				 		  		 				 		  		 			        }
			return null;
		}

	}

 
    
    
    
    
    
     
    
     
    
    
    
    
    
    
    
       
     
    
    
    
      
    
    
    }
