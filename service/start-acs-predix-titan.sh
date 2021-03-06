#!/bin/bash
#*******************************************************************************
# Copyright 2016 General Electric Company. 
#
# Licensed under the Apache License, Version 2.0 (the "License"); 
# you may not use this file except in compliance with the License. 
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software 
# distributed under the License is distributed on an "AS IS" BASIS, 
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
# See the License for the specific language governing permissions and 
# limitations under the License.
#*******************************************************************************

export PROXY_OPTS=${PROXY_OPTS}
export DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
export ACS_SERVICE_ID=${ACS_SERVICE_ID}
export ACS_DEFAULT_ISSUER_ID=${ACS_DEFAULT_ISSUER_ID}
export ACS_BASE_DOMAIN=${ACS_BASE_DOMAIN}

export ZONE2_UAA_URL=${ZONE2_UAA_URL}

main() {
    while [ "$1" != "" ]; do
        case $1 in
            'debug')
                JAVA_DEBUG_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
                shift
                ;;
            *)
                break
                ;;
        esac
    done
 
    cp ${DIR}/target/acs-service-*.jar ${DIR}/.acs-service-copy.jar
    SPRING_PROFILES_ACTIVE=h2,predix,simple-cache,titan \
    java -Xms1g -Xmx1g $JAVA_DEBUG_OPTS $PROXY_OPTS -jar ${DIR}/.acs-service-copy.jar   
}

main "$@"