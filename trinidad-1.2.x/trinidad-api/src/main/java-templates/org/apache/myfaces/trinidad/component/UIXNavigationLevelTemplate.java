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
package org.apache.myfaces.trinidad.component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.myfaces.trinidad.component.visit.VisitCallback;
import org.apache.myfaces.trinidad.component.visit.VisitContext;


/**
 * Base class for the NavigationLevel component.
 *
 * @version $Name:  $ ($Revision: 988615 $) $Date: 2010-08-24 11:33:13 -0500 (Mar, 24 Ago 2010) $
 */
abstract public class UIXNavigationLevelTemplate
  extends UIXNavigationHierarchy
{
/**/ // Abstract methods implemented by code gen
/**/  abstract public int getLevel();

  @Override
  protected void processFacetsAndChildren(
    FacesContext context,
    PhaseId phaseId)
  {
    Object oldPath = getRowKey();
    HierarchyUtils.__setStartDepthPath(this, getLevel());
    
    // process stamp for one level
    HierarchyUtils.__processLevel(context, phaseId, this);
    setRowKey(oldPath);

    // process the children
    TableUtils.__processChildren(context, this, phaseId);
  }

  @Override
  protected boolean visitChildren(
    VisitContext  visitContext,
    VisitCallback callback)
  {
    boolean done = visitData(visitContext, callback);

    if (!done)
    {
      // process the children
      int childCount = getChildCount();
      if (childCount > 0)
      {
        for (UIComponent child : getChildren())
        {
          done = UIXComponent.visitTree(visitContext, child, callback);
          
          if (done)
            break;
        }
      }          
    }
    
    return done;
  }
  
  @Override
  protected boolean visitData(
    VisitContext  visitContext,
    VisitCallback callback)
  {
    Object oldKey = getRowKey();
       
    boolean done;

    HierarchyUtils.__setStartDepthPath(this, getLevel());

    try
    {
      done = visitLevel(visitContext, callback, getStamps());
    }
    finally
    {
      setRowKey(oldKey);
    }
    
    return done;
  }
}
