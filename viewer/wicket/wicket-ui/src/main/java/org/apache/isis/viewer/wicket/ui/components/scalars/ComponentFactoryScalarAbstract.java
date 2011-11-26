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

package org.apache.isis.viewer.wicket.ui.components.scalars;

import java.util.List;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.ComponentFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.isis.viewer.wicket.ui.components.widgets.valuechoices.ValueChoicesPanel;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public abstract class ComponentFactoryScalarAbstract extends ComponentFactoryAbstract {

    private static final long serialVersionUID = 1L;

    private final Class<?>[] scalarTypes;

    public ComponentFactoryScalarAbstract(final Class<?>... scalarTypes) {
        super(ComponentType.SCALAR_NAME_AND_VALUE);
        this.scalarTypes = scalarTypes;
    }

    @Override
    public ApplicationAdvice appliesTo(final IModel<?> model) {
        if (!(model instanceof ScalarModel)) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        final ScalarModel scalarModel = (ScalarModel) model;
        return appliesIf(scalarModel.isScalarTypeAnyOf(scalarTypes));
    }

    int choiceCount = 0;

    @Override
    public final Component createComponent(final String id, final IModel<?> model) {
        final ScalarModel scalarModel = (ScalarModel) model;
        // return createComponent(id, scalarModel);

        // TODO: This is where the ValueChoicesPanel panel gets created.
        final List<ObjectAdapter> choices = scalarModel.getChoices();
        if (choices.size() > 0) {
            return new ValueChoicesPanel(id, scalarModel);
        } else {
            return createComponent(id, scalarModel);
        }
    }

    protected abstract Component createComponent(String id, ScalarModel scalarModel);

}