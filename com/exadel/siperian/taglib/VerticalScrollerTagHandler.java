/**
 * GENERATED FILE - DO NOT EDIT
 *
 */

package com.exadel.siperian.taglib;

import javax.faces.component.UIComponent ;
import java.lang.Object ;
import com.exadel.siperian.taglib.VerticalScrollerTagHandlerBase ;
import java.lang.String ;
import javax.el.MethodExpression ;
import javax.faces.el.MethodBinding ;
import javax.faces.component.UIComponent;
import com.exadel.siperian.component.html.HtmlVerticalScroller;
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
public class VerticalScrollerTagHandler extends com.exadel.siperian.taglib.VerticalScrollerTagHandlerBase {


  private static final VerticalScrollerTagHandlerMetaRule metaRule = new VerticalScrollerTagHandlerMetaRule();


  
  public VerticalScrollerTagHandler(ComponentConfig config) 
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
	static class VerticalScrollerTagHandlerMetaRule extends MetaRule{

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.MetaRule#applyRule(java.lang.String, com.sun.facelets.tag.TagAttribute, com.sun.facelets.tag.MetadataTarget)
		 */
		public Metadata applyRule(String name, TagAttribute attribute, MetadataTarget meta) {
	        if (meta.isTargetInstanceOf(HtmlVerticalScroller.class)) {
				 		  	            	            
	            if ("action".equals(name)) {
	                    return new actionMapper(attribute);
	            }
				
						  		 				 		  	            		
				//skip actionExpression
	
						  		 				 		  	            	            
	            if ("actionListener".equals(name)) {
	                    return new actionListenerMapper(attribute);
	            }
				
						  		 				 				 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 				 		  		 				 				 		  		 				 				 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 				 		  		 				 				 				 		  		 				 				 		  		 				 				 				 				 				 				 				 				 				 				 		  		 				 		  		 				 				 				 		  		 				 		  		 				 				 		  		 				 		  		 				 		  		 				 				 		  		 				 				 		  	            	            
	            if ("scrollerListener".equals(name)) {
	                    return new scrollerListenerMapper(attribute);
	            }
				
						  		 				 				 		  		 				 		  		 				 		  		 				 		  		 				 		  		 				 				 				 		  		 				 		  		 				 		  		 				 		  		 			        }
			return null;
		}

	}


  		
	static class actionMapper extends Metadata {

		private static final Class[] SIGNATURE = new Class[] {};

		private final TagAttribute _action;
		/**
		 * @param attribute
		 */
		public actionMapper(TagAttribute attribute) {
			_action = attribute;
		}

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.Metadata#applyMetadata(com.sun.facelets.FaceletContext, java.lang.Object)
		 */
		public void applyMetadata(FaceletContext ctx, Object instance) {
            ((HtmlVerticalScroller) instance)
            .setAction
            	            		(new LegacyMethodBinding(this._action.getMethodExpression(ctx, null,
                            SIGNATURE)));
            			}

	}
	
		
	  
  		
	  
  		
	static class actionListenerMapper extends Metadata {

		private static final Class[] SIGNATURE = new Class[] {javax.faces.event.ActionEvent.class};

		private final TagAttribute _action;
		/**
		 * @param attribute
		 */
		public actionListenerMapper(TagAttribute attribute) {
			_action = attribute;
		}

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.Metadata#applyMetadata(com.sun.facelets.FaceletContext, java.lang.Object)
		 */
		public void applyMetadata(FaceletContext ctx, Object instance) {
            ((HtmlVerticalScroller) instance)
            .setActionListener
            	            		(new LegacyMethodBinding(this._action.getMethodExpression(ctx, null,
                            SIGNATURE)));
            			}

	}
	
		
	    
    
     
    
    
     
     
      
     
    
    
    
    
     
    
    
    
     
      
     
             
    
      
    
     
    
    
     
     
  		
	static class scrollerListenerMapper extends Metadata {

		private static final Class[] SIGNATURE = new Class[] {org.richfaces.event.DataScrollerEvent.class};

		private final TagAttribute _action;
		/**
		 * @param attribute
		 */
		public scrollerListenerMapper(TagAttribute attribute) {
			_action = attribute;
		}

		/* (non-Javadoc)
		 * @see com.sun.facelets.tag.Metadata#applyMetadata(com.sun.facelets.FaceletContext, java.lang.Object)
		 */
		public void applyMetadata(FaceletContext ctx, Object instance) {
            ((HtmlVerticalScroller) instance)
            .setScrollerListener
            	            		(this._action.getMethodExpression(ctx, null,
                            SIGNATURE));
            			}

	}
	
		
	   
    
    
    
    
      
    
    
    
    }
