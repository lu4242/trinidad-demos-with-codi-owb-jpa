/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.myfaces.trinidaddemo.components.layout.panelGroupLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupLayoutDynamicBean {

	private List<String> layoutItems;

	/**
	 * Constructor.
	 */
	public GroupLayoutDynamicBean() {
		layoutItems = new ArrayList<String>();

		layoutItems.add("Dynamic Item 1");
		layoutItems.add("Dynamic Item 2");
		layoutItems.add("Dynamic Item 3");
		layoutItems.add("Dynamic Item 4");
		layoutItems.add("Dynamic Item 5");
	}

	/**
	 * @return the layoutItems
	 */
	public List<String> getLayoutItems() {
		return layoutItems;
	}	
}
