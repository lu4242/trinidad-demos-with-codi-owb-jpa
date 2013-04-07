/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.myfaces.trinidadinternal.renderkit.core.pda;

import java.io.IOException;

import java.util.Collections;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.myfaces.trinidad.bean.FacesBean;
import org.apache.myfaces.trinidad.component.core.nav.CoreCommandLink;
import org.apache.myfaces.trinidad.context.RenderingContext;

import org.apache.myfaces.trinidadinternal.renderkit.core.xhtml.CommandLinkRenderer;
import org.apache.myfaces.trinidadinternal.renderkit.core.xhtml.SkinSelectors;
import org.apache.myfaces.trinidadinternal.renderkit.core.xhtml.OutputUtils;
import org.apache.myfaces.trinidadinternal.renderkit.core.xhtml.TrinidadRenderingConstants;
import org.apache.myfaces.trinidadinternal.renderkit.core.xhtml.XhtmlUtils;

/**
 * On PDA,just render as input element of submit type if the browser 
 * doesn't support javascript.
 */

public class PdaCommandLinkRenderer extends CommandLinkRenderer
{
  public PdaCommandLinkRenderer()
  {
    super(CoreCommandLink.TYPE);
  }

  @Override
  public boolean getRendersChildren()
  {
    return true;
  }

  @Override
  protected void encodeAll(
    FacesContext        context,
    RenderingContext    arc,
    UIComponent         component,
    FacesBean           bean) throws IOException
  {
    if (supportsScripting(arc))
    {
      encodeBegin(context, arc, component, bean);
      encodeAllChildren(context, component);
      encodeEnd(context, arc, component, bean);
      return;
    }

   
     // For Non-JavaScript browsers render the commandLink as
     // input submit element
     
    String clientId = getClientId(context, component);
    if (canSkipRendering(arc, clientId))
      return;

    // Set client ID
    assert(arc.getCurrentClientId() == null);
    arc.setCurrentClientId(clientId);

    ResponseWriter rw = context.getResponseWriter();
    String element = "input";
    rw.startElement(element, component);
    renderId(context, component);

    // Write the text and access key
    String text = getText(bean);
    rw.writeAttribute("type", "submit", null);
    
    if (getDisabled(bean))
    {
      rw.writeAttribute("disabled", Boolean.TRUE, "disabled");
      // Skip over event attributes when disabled
      renderStyleAttributes(context, arc, bean);
    }
    else
    {
      renderAllAttributes(context, arc, bean);
    }

    char accessKey;
    if (supportsAccessKeys(arc))
    {
      accessKey = getAccessKey(bean);
      if (accessKey != CHAR_UNDEFINED)
      {
        rw.writeAttribute("accesskey",
                             Character.valueOf(accessKey),
                             "accessKey");
      }
    }
    else
    {
      accessKey = CHAR_UNDEFINED;
    }
    rw.writeAttribute("name", XhtmlUtils.getEncodedParameter
                                   ( TrinidadRenderingConstants.SOURCE_PARAM )
                                   +  clientId, null);

    rw.writeAttribute("id", clientId , "id");
    rw.writeAttribute("value", text, "text");
    renderStyleClass(context, arc, 
                  SkinSelectors.AF_COMMAND_BUTTON_STYLE_CLASS);
    String style = getInlineStyle(bean);
        
    // Append an inlineStyle that makes an input element appear as a link
    _writeInlineStyles(rw, style,
            "border:none;background:inherit;text-decoration:underline;");
    rw.endElement(element);
    arc.setCurrentClientId(null);
  }
  
  /**
   * Renders the client ID as both "id" and "name"
   * @param context the FacesContext object
   * @param component the UIComponent object
   * @throws IOException
   */
  @Override
  protected void renderId(
    FacesContext context,
    UIComponent  component) throws IOException
  { 
    if (shouldRenderId(context, component))
    {
      String clientId = getClientId(context, component);
      context.getResponseWriter().writeURIAttribute("id", clientId, "id");
      RenderingContext arc = RenderingContext.getCurrentInstance();
      // For Non-JavaScript browsers, name attribute is handled separately 
      // so skip it here
      if (supportsScripting(arc))
      {
        context.getResponseWriter().writeURIAttribute("name", clientId, "id");
      }
    } 
  }

  
  /**
   * This method renders the stylesClass attribute
   * @param FacesContext - FacesContext for this request
   * @param arc - RenderingContext for this request
   * @param bean - FacesBean of the component to render
   * @param defaultStyleClass - default styleClass of the component  
   */
  @Override
  protected void renderStyleAttributes(
    FacesContext        context,
    RenderingContext    arc,
    FacesBean           bean,
    String              defaultStyleClass) throws IOException
  { 
    // Skip for HTML basic browsers since it is already handled
    if (supportsScripting(arc))
    {
      super.renderStyleAttributes(context, arc, bean, defaultStyleClass);
    }
  } 
  
  /**
   * This method renders the inlineStyle attribute
   * @param rw - the response writer
   * @param userInlineStyle - the value of inlineStyle attribute
   * @param appendedInlineStyle - inlineStyle that is applied by default
   */
  private void _writeInlineStyles(
    ResponseWriter rw,
    String userInlineStyle,
    String appendedInlineStyle ) throws IOException
  {
    if (userInlineStyle == null)
    {
      rw.writeAttribute("style", appendedInlineStyle, "inlineStyle");
    }
    else
    {
      StringBuilder linkInlineStyle = new StringBuilder();
      linkInlineStyle.append(appendedInlineStyle);
      linkInlineStyle.append(userInlineStyle.trim());
      rw.writeAttribute("style", linkInlineStyle.toString(), "inlineStyle");
    }
  }
  
}
