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

package org.apache.myfaces.trinidad.component.visit;

/**
 *
 * <p>An enum that specifies the possible 
 * results of a call to {@link VisitCallback#visit VisitCallback.visit()}.
 * </p>
 *
 * @see VisitCallback#visit VisitCallback.visit()
 */
public enum VisitResult
{
  /**
   * This result indicates that the tree visit should descend into
   * current component's subtree.
   */ 
  ACCEPT,

  /**
   * This result indicates that the tree visit should continue, but
   * should skip the current component's subtree.
   */ 
  REJECT,

  /**
   * This result indicates that the tree visit should be terminated.
   */ 
  COMPLETE
}
