/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.isis.viewer.json.viewer.resources.user;

import org.apache.isis.core.commons.authentication.AuthenticationSession;
import org.apache.isis.viewer.json.applib.JsonRepresentation;
import org.apache.isis.viewer.json.applib.RepresentationType;
import org.apache.isis.viewer.json.viewer.ResourceContext;
import org.apache.isis.viewer.json.viewer.representations.PathFollower;
import org.apache.isis.viewer.json.viewer.representations.ReprRenderer;
import org.apache.isis.viewer.json.viewer.representations.ReprRendererAbstract;
import org.apache.isis.viewer.json.viewer.representations.ReprRendererFactoryAbstract;

public class UserReprRenderer extends ReprRendererAbstract<UserReprRenderer, AuthenticationSession> {

    public static class Factory extends ReprRendererFactoryAbstract {

        public Factory() {
            super(RepresentationType.USER);
        }

        @Override
        public ReprRenderer<?, ?> newRenderer(ResourceContext resourceContext, PathFollower pathFollower, JsonRepresentation representation) {
            return new UserReprRenderer(resourceContext, pathFollower, getRepresentationType(), representation);
        }
    }
    
    private UserReprRenderer(ResourceContext resourceContext, PathFollower pathFollower, RepresentationType representationType, JsonRepresentation representation) {
        super(resourceContext, pathFollower, representationType, representation);
    }

    @Override
    public UserReprRenderer with(AuthenticationSession authenticationSession) {
        representation.mapPut("username", authenticationSession.getUserName());
        JsonRepresentation roles = JsonRepresentation.newArray();
        for (String role : authenticationSession.getRoles()) {
            roles.arrayAdd(role);
        }
        representation.mapPut("roles", roles);
        return this;
    }

    public JsonRepresentation render() {
        if(includesSelf) {
            withSelf("user");
        }
        withLinks();
        withExtensions();
        return representation;
    }

}