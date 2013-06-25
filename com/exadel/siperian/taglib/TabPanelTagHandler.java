/**
 * GENERATED FILE - DO NOT EDIT
 *
 */

package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import javax.faces.convert.Converter ;
import java.lang.Object ;
import java.util.List ;
import com.exadel.siperian.taglib.TabPanelTagHandlerBase ;
import java.lang.Integer ;
import java.lang.String ;
import javax.faces.el.MethodBinding ;
import javax.faces.component.UIComponent;
import com.exadel.siperian.component.html.HtmlTabPanel;
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
public class TabPanelTagHandler extends com.exadel.siperian.taglib.TabPanelTagHandlerBase {


  private static final TabPanelTagHandlerMetaRule metaRule = new TabPanelTagHandlerMetaRule();


  
  public TabPanelTagHandler(ComponentConfig config) 
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
	static class TabPanelTagHandlerMetaRule extends MetaRule{

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.MetaRule#applyRule(java.lang.String, com.sun.facelets.tag.TagAttribute, com.sun.facelets.tag.MetadataTarget)
		 */
		public Metadata applyRule(String name, TagAttribute attribute, MetadataTarget meta) {
	        if (meta.isTargetInstanceOf(HtmlTabPanel.class)) {
				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 				 				 				 				 				 				 				 				 		  		 				 		  		 				 				 				 		  		 				 		  		 				 		  		 				 				 				 				 		  		 				 		  		 				 		  	            	            
	            if ("tabCloseListener".equals(name)) {
	                    return new tabCloseListenerMapper(attribute);
	            }
				
						  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  	            	            
	            if ("validator".equals(name)) {
	                    return new validatorMapper(attribute);
	            }
				
						  		 				 		  		 				 				 				 		  	            	            
	            if ("valueChangeListener".equals(name)) {
	                    return new valueChangeListenerMapper(attribute);
	            }
				
						  		 				 				 		  		 			        }
			return null;
		}

	}


     
    
    
    
     
     
    
    
    
     
    
    
      
    
    
    
    
    
              
    
      
    
    
       
    
    
  		
	static class tabCloseListenerMapper extends Metadata {

		private static final Class[] SIGNATURE = new Class[] {java.lang.String.class};

		private final TagAttribute _action;
		/**
		 * @param attribute
		 */
		public tabCloseListenerMapper(TagAttribute attribute) {
			_action = attribute;
		}

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.Metadata#applyMetadata(com.sun.facelets.FaceletContext, java.lang.Object)
		 */
		public void applyMetadata(FaceletContext ctx, Object instance) {
            ((HtmlTabPanel) instance)
            .setTabCloseListener
            	            		(new LegacyMethodBinding(this._action.getMethodExpression(ctx, null,
                            SIGNATURE)));
            			}

	}
	
		
	  
    
    
     
    
  		
	static class validatorMapper extends Metadata {

		private static final Class[] SIGNATURE = new Class[] {javax.faces.context.FacesContext.class,javax.faces.component.UIComponent.class,java.lang.Object.class};

		private final TagAttribute _action;
		/**
		 * @param attribute
		 */
		public validatorMapper(TagAttribute attribute) {
			_action = attribute;
		}

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.Metadata#applyMetadata(com.sun.facelets.FaceletContext, java.lang.Object)
		 */
		public void applyMetadata(FaceletContext ctx, Object instance) {
            ((HtmlTabPanel) instance)
            .setValidator
            	            		(new LegacyMethodBinding(this._action.getMethodExpression(ctx, null,
                            SIGNATURE)));
            			}

	}
	
		
	  
      
  		
	static class valueChangeListenerMapper extends Metadata {

		private static final Class[] SIGNATURE = new Class[] {javax.faces.event.ValueChangeEvent.class};

		private final TagAttribute _action;
		/**
		 * @param attribute
		 */
		public valueChangeListenerMapper(TagAttribute attribute) {
			_action = attribute;
		}

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.Metadata#applyMetadata(com.sun.facelets.FaceletContext, java.lang.Object)
		 */
		public void applyMetadata(FaceletContext ctx, Object instance) {
            ((HtmlTabPanel) instance)
            .setValueChangeListener
            	            		(new LegacyMethodBinding(this._action.getMethodExpression(ctx, null,
                            SIGNATURE)));
            			}

	}
	
		
	   
    }
