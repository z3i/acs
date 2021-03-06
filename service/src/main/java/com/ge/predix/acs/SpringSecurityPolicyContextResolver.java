/*******************************************************************************
 * Copyright 2016 General Electric Company.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.ge.predix.acs;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import com.ge.predix.acs.service.InvalidACSRequestException;

/**
 * Retrieve the authentication context.
 *
 * @author 212406427
 */
@SuppressWarnings("nls")
@Component
public class SpringSecurityPolicyContextResolver implements PolicyContextResolver {
    @Override
    public String getIssuerIdOrFail() {
        String issuer = null;
        OAuth2Request oAuth2Request = getAuthentication().getOAuth2Request();

        if (oAuth2Request != null) {
            Map<String, String> requestParameters = oAuth2Request.getRequestParameters();

            if (requestParameters != null && requestParameters.containsKey("iss")) {
                issuer = requestParameters.get("iss");
            }
        }

        if (issuer == null) {
            throw new InvalidACSRequestException("Authetication issuer cannot be null");
        }

        return issuer;
    }

    @Override
    public String getClientIdOrFail() {
        String clientId = null;

        OAuth2Request oAuth2Request = getAuthentication().getOAuth2Request();
        if (oAuth2Request != null) {
            clientId = oAuth2Request.getClientId();
        }
        if (clientId == null) {
            throw new InvalidACSRequestException("Authetication clientId cannot be null");
        }

        return clientId;
    }

    private OAuth2Authentication getAuthentication() {
        OAuth2Authentication oAuth2authentication = (OAuth2Authentication) SecurityContextHolder.getContext()
                .getAuthentication();

        // postcondition check that the oAuth2authentication should never be
        // null
        if (oAuth2authentication == null) {
            throw new InvalidACSRequestException("OAuth2Authentication cannot be null");
        }

        return oAuth2authentication;
    }

}
