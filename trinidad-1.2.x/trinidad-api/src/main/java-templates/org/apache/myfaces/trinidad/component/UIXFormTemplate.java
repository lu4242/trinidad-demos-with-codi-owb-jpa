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

import javax.faces.context.FacesContext;

/**
 * Base class for Form component.
 * <p>
 * @version $Name:  $ ($Revision: 518820 $) $Date: 2007-03-15 20:02:36 -0500 (Jue, 15 Mar 2007) $
 */
abstract public class UIXFormTemplate extends UIXComponentBase
{
/**/ // Abstract methods implemented by code gen
/**/  abstract public boolean isSubmitted();
  
  @Override
  public void processDecodes(FacesContext context)
  {
    if (!isRendered())
      return;

    decode(context);
    if (!isSubmitted())
      return;

    decodeChildren(context);
  }

  @Override
  public void processValidators(FacesContext context)
  {
    if (isSubmitted())
      super.processValidators(context);
  }

  @Override
  public void processUpdates(FacesContext context)
  {
    if (isSubmitted())
      super.processUpdates(context);
  }
}
