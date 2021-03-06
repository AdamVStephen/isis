/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package app;

import dom.todo.ToDoItem;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotContributed.As;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.annotation.SemanticsOf;

@DomainService
public class ToDoItemAnalysisContributions {


    //region > analyseCategory (action)
    @NotInServiceMenu
    @NotContributed(As.ASSOCIATION)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    public ToDoItemsByCategoryViewModel analyseCategory(final ToDoItem item) {
        return toDoAppAnalysis.toDoItemsForCategory(item.getCategory());
    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private ToDoItemAnalysis toDoAppAnalysis;

    //endregion

}
